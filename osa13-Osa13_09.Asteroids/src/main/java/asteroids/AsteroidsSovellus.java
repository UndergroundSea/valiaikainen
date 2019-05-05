package asteroids;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
//import javafx.scene.shape.Circle;
//import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class AsteroidsSovellus extends Application {

    public static int LEVEYS = 600;
    public static int KORKEUS = 400;

    @Override
    public void start(Stage stage) throws Exception {
        Pane ruutu = new Pane();
        Text text = new Text(10, 20, "Points: 0");
        AtomicInteger pisteet = new AtomicInteger();
        ruutu.getChildren().add(text);

        ruutu.setPrefSize(LEVEYS, KORKEUS);

        Alus alus = new Alus(LEVEYS / 2, KORKEUS / 2);
        List<Asteroidi> asteroidit = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Random rnd = new Random();
            Asteroidi asteroidi = new Asteroidi(rnd.nextInt(LEVEYS / 3), rnd.nextInt(KORKEUS));
            asteroidit.add(asteroidi);
        }
        List<Ammus> ammukset = new ArrayList<>();

        Map<KeyCode, Boolean> painetutNapit = new HashMap<>();

        ruutu.getChildren().add(alus.getHahmo());
        asteroidit.forEach(asteroidi -> ruutu.getChildren().add(asteroidi.getHahmo()));

        Scene scene = new Scene(ruutu);
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(event -> {
            painetutNapit.put(event.getCode(), Boolean.TRUE);
        });
        scene.setOnKeyReleased(event -> {
            painetutNapit.put(event.getCode(), Boolean.FALSE);
        });

        Point2D liike = new Point2D(1, 0);

        List<Ammus> poistettavatAmmukset = ammukset.stream().filter(ammus -> {
            List<Asteroidi> tormatyt = asteroidit.stream()
                    .filter(asteroidi -> asteroidi.tormaa(ammus))
                    .collect(Collectors.toList());

            if (tormatyt.isEmpty()) {
                return false;
            }

            tormatyt.stream().forEach(tormatty -> {
                asteroidit.remove(tormatty);
                ruutu.getChildren().remove(tormatty.getHahmo());
            });

            return true;
        }).collect(Collectors.toList());

        poistettavatAmmukset.forEach(ammus -> {
            ruutu.getChildren().remove(ammus.getHahmo());
            ammukset.remove(ammus);
        });

        new AnimationTimer() {

            @Override
            public void handle(long nykyhetki) {
                if (painetutNapit.getOrDefault(KeyCode.LEFT, false)) {
                    alus.kaannaVasemmalle();
                }

                if (painetutNapit.getOrDefault(KeyCode.RIGHT, false)) {
                    alus.kaannaOikealle();
                }

                if (painetutNapit.getOrDefault(KeyCode.UP, false)) {
                    alus.kiihdyta();
                }

                alus.liiku();
                ammukset.forEach(ammus -> {
                    ammus.liiku();
                    ammus.kiihdyta();
                });
                ammukset.forEach(ammus -> {
                    asteroidit.forEach(asteroidi -> {
                        if (asteroidi.tormaa(ammus)) {
                            asteroidit.remove(asteroidi);
                            ammukset.remove(ammus);
                            ruutu.getChildren().remove(ammus.getHahmo());
                            ruutu.getChildren().remove(asteroidi.getHahmo());
                            text.setText("Pisteet: " + pisteet.addAndGet(500));
                        }
                    });
                });
                asteroidit.forEach(asteroidi -> asteroidi.liiku());

                asteroidit.forEach(asteroidi -> {
                    if (alus.tormaa(asteroidi)) {
                        stop();
                    }

                });

                if (painetutNapit.getOrDefault(KeyCode.SPACE, false) && ammukset.size() < 3) {
                    // ammutaan
                    Ammus ammus = new Ammus((int) alus.getHahmo().getTranslateX(), (int) alus.getHahmo().getTranslateY());
                    ammus.getHahmo().setRotate(alus.getHahmo().getRotate());
                    ammukset.add(ammus);

                    ammus.kiihdyta();
                    ammus.setLiike(ammus.getLiike().normalize().multiply(3));

                    ruutu.getChildren().add(ammus.getHahmo());
                }

                text.setText("Pisteet: " + pisteet.incrementAndGet());

                if (Math.random() < 0.005) {
                    Asteroidi asteroidi = new Asteroidi(LEVEYS, KORKEUS);
                    if (!asteroidi.tormaa(alus)) {
                        asteroidit.add(asteroidi);
                        ruutu.getChildren().add(asteroidi.getHahmo());
                    }
                }

            }

        }.start();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static int osiaToteutettu() {
        return 4;
    }

}
