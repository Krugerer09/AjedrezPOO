/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.chessg12;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kruro
 */
public class TableroController implements Initializable {

    @FXML
    private GridPane Tablero;
    private Button[][] b = new Button[8][8];
    private static boolean turnoBlanco = true; // Jugador blanco inicia

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                b[i][j] = new Button();
                b[i][j].setPrefHeight(100);
                b[i][j].setPrefWidth(100);
                //inicializacion de tablero
                final int row = i;
                final int col = j;

                b[i][j].setOnMouseClicked(event -> {

                    if (Modelo.listoMover == false) {
                        if (b[row][col].getGraphic() != null) {
                            Modelo.imagePiezaI = (ImageView) b[row][col].getGraphic();

                            Modelo.posicionXInicial = col;
                            Modelo.posicionYInicial = row;
                            if ((turnoBlanco && piezaBlanca(Modelo.imagePiezaI)) || (!turnoBlanco && !piezaBlanca(Modelo.imagePiezaI))) {

                                Modelo.listoMover = true;
                            }

                        }

                    } else {

                        Modelo.listoMover = false;

                        //MOVERSE SIN COMER
                        if (b[row][col].getGraphic() == null) {
                            Modelo.posicionXfinal = col;
                            Modelo.posicionYfinal = row;

                            if (esCaballo()) {
                                //movimientosCaballo
                                if (movimientosCaballo(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row)) {
                                    b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                    b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                    turnoBlanco = !turnoBlanco;
                                }
                            }
                            //movimientoPeon
                            if (esPeon()) {

                                if (movimientosPeon(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row, Modelo.imagePiezaI, Modelo.imagePiezaF)) {
                                    b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                    b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                    turnoBlanco = !turnoBlanco;
                                }
                            }
                            if (esTorre()) {
                                if (movimientoTorre(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row)) {
                                    b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                    b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                    turnoBlanco = !turnoBlanco;
                                }

                            }
                            if (esReina()) {
                                if (movimientoReina(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row)) {
                                    b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                    b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                    turnoBlanco = !turnoBlanco;
                                }

                            }
                            if (esBishop()) {
                                if (movimientoBishop(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row)) {

                                    b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                    b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                    turnoBlanco = !turnoBlanco;

                                }

                            }
                            if (esRey()) {

                                if (movimientoRey(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row)) {

                                    b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                    b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                    turnoBlanco = !turnoBlanco;

                                }

                            }
                            //COMER
                        } else {
                            Modelo.imagePiezaF = (ImageView) b[row][col].getGraphic();
                            //blanca a negra
                            if (piezaBlanca(Modelo.imagePiezaI)) {
                                if (!(piezaBlanca(Modelo.imagePiezaF))) {
                                    if (esPeon()) {
                                        if (comerPeon(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row, Modelo.imagePiezaI)) {
                                            b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                            b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                            turnoBlanco = !turnoBlanco;
                                        }

                                    }
                                    if (esCaballo()) {
                                        if (movimientosCaballo(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row)) {
                                            b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                            b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                            turnoBlanco = !turnoBlanco;
                                        }

                                    }
                                    if (esTorre()) {

                                        if (movimientoTorre(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row)) {
                                            b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                            b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                            turnoBlanco = !turnoBlanco;
                                        }

                                    }
                                    if (esReina()) {
                                        if (movimientoReina(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row)) {
                                            b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                            b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                            turnoBlanco = !turnoBlanco;
                                        }

                                    }
                                    if (esBishop()) {
                                        if (movimientoBishop(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row)) {

                                            b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                            b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                            turnoBlanco = !turnoBlanco;

                                        }

                                    }
                                    if (esRey()) {

                                        if (movimientoRey(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row)) {

                                            b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                            b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                            turnoBlanco = !turnoBlanco;

                                        }

                                    }

                                }

                            } else if (!(piezaBlanca(Modelo.imagePiezaI))) {
                                //negra a blanca
                                if (piezaBlanca(Modelo.imagePiezaF)) {
                                    if (esPeon()) {
                                        if (comerPeon(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row, Modelo.imagePiezaI)) {
                                            b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                            b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                            turnoBlanco = !turnoBlanco;
                                        }

                                    }
                                    if (esCaballo()) {
                                        if (movimientosCaballo(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row)) {
                                            b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                            b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                            turnoBlanco = !turnoBlanco;
                                        }

                                    }
                                    if (esReina()) {
                                        if (movimientoReina(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row)) {
                                            b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                            b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                            turnoBlanco = !turnoBlanco;
                                        }

                                    }
                                    if (esTorre()) {

                                        if (movimientoTorre(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row)) {
                                            b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                            b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                            turnoBlanco = !turnoBlanco;
                                        }

                                    }
                                    if (esBishop()) {
                                        if (movimientoBishop(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row)) {

                                            b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                            b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                            turnoBlanco = !turnoBlanco;

                                        }

                                    }
                                    if (esRey()) {

                                        if (movimientoRey(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row)) {

                                            b[row][col].setGraphic(b[Modelo.posicionYInicial][Modelo.posicionXInicial].getGraphic());
                                            b[Modelo.posicionYInicial][Modelo.posicionXInicial].setGraphic(null);
                                            turnoBlanco = !turnoBlanco;

                                        }

                                    }

                                }
                            }

                        }
                        if (jaque()) {
                            System.out.println("en jaque");
                        }

                        promocionPeon(Modelo.posicionXInicial, Modelo.posicionYInicial, col, row);

                        System.out.println(col);
                        System.out.println(row);

                    }
                }
                );

                //peon blanco
                if (j == 6) {
                    Image peonB = new Image("/ec/edu/espol/chessg12/imagenes/peon blanco.png");

                    ImageView peonBlanco1 = new ImageView(peonB);
                    peonBlanco1.setFitWidth(80);
                    peonBlanco1.setFitHeight(80);

                    b[i][6].setGraphic(peonBlanco1);
                    Modelo.peonB = peonBlanco1;

                }

                //peon negro
                if (j == 1) {
                    Image peonN = new Image("/ec/edu/espol/chessg12/imagenes/peon negro.png");

                    ImageView peonNegro1 = new ImageView(peonN);
                    peonNegro1.setFitWidth(80);
                    peonNegro1.setFitHeight(80);

                    b[i][1].setGraphic(peonNegro1);
                    Modelo.peonN = peonNegro1;

                }
                // piezas blancas
                if (j == 7) {
                    // torres blancas
                    if (i == 0 || i == 7) {

                        Image TorreBlanca = new Image("/ec/edu/espol/chessg12/imagenes/torre blanca.png");
                        ImageView torreBlanca1 = new ImageView(TorreBlanca);
                        torreBlanca1.setFitWidth(80);
                        torreBlanca1.setFitHeight(80);
                        b[i][j].setGraphic(torreBlanca1);
                        Modelo.torreBlanca = torreBlanca1;

                    }
                    // caballos blancos
                    if (i == 1 || i == 6) {
                        Image caballoBlanco = new Image("/ec/edu/espol/chessg12/imagenes/caballo blanco.png");

                        ImageView caballoBlanco1 = new ImageView(caballoBlanco);
                        caballoBlanco1.setFitWidth(80);
                        caballoBlanco1.setFitHeight(80);
                        b[i][j].setGraphic(caballoBlanco1);
                        Modelo.caballoBlanco = caballoBlanco1;

                    }
                    // bishops blancos
                    if (i == 2 || i == 5) {
                        Image bishopBlanco = new Image("/ec/edu/espol/chessg12/imagenes/bishop blanco.png");

                        ImageView bishopBlanco1 = new ImageView(bishopBlanco);
                        bishopBlanco1.setFitWidth(80);
                        bishopBlanco1.setFitHeight(80);
                        b[i][j].setGraphic(bishopBlanco1);
                        Modelo.bishopBlanco = bishopBlanco1;
                    }
                    // reina blanca
                    if (i == 3) {
                        Image reynaB = new Image("/ec/edu/espol/chessg12/imagenes/reina blanca.png");

                        ImageView reinaBlanca = new ImageView(reynaB);
                        reinaBlanca.setFitWidth(80);
                        reinaBlanca.setFitHeight(80);
                        b[i][j].setGraphic(reinaBlanca);
                        Modelo.reinaB = reinaBlanca;
                    }
                    if (i == 4) {
                        Image reyB = new Image("/ec/edu/espol/chessg12/imagenes/rey blanco.png");

                        ImageView reyBlanco = new ImageView(reyB);
                        reyBlanco.setFitWidth(80);
                        reyBlanco.setFitHeight(80);
                        b[i][j].setGraphic(reyBlanco);
                        Modelo.reyB = reyBlanco;

                    }

                }
                //piezas negras
                if (j == 0) {
                    // torres negra
                    if (i == 0 || i == 7) {

                        Image TorreNegra = new Image("/ec/edu/espol/chessg12/imagenes/torre negra.png");

                        ImageView torreNegra1 = new ImageView(TorreNegra);
                        torreNegra1.setFitWidth(80);
                        torreNegra1.setFitHeight(80);
                        b[i][j].setGraphic(torreNegra1);
                        Modelo.torreNegra = torreNegra1;

                    }
                    // caballos negros
                    if (i == 1 || i == 6) {
                        Image caballoNegro = new Image("/ec/edu/espol/chessg12/imagenes/caballo negro.png");

                        ImageView caballoNegro1 = new ImageView(caballoNegro);
                        caballoNegro1.setFitWidth(80);
                        caballoNegro1.setFitHeight(80);
                        b[i][j].setGraphic(caballoNegro1);
                        Modelo.caballoNegro = caballoNegro1;
                    }
                    // bishops negros
                    if (i == 2 || i == 5) {
                        Image bishopNegro = new Image("/ec/edu/espol/chessg12/imagenes/bishop negro.png");

                        ImageView bishopNegro1 = new ImageView(bishopNegro);
                        bishopNegro1.setFitWidth(80);
                        bishopNegro1.setFitHeight(80);
                        b[i][j].setGraphic(bishopNegro1);
                        Modelo.bishopNegro = bishopNegro1;

                    }
                    // reina negro
                    if (i == 3) {
                        Image reinaN = new Image("/ec/edu/espol/chessg12/imagenes/reina negra.png");

                        ImageView reinaNegra = new ImageView(reinaN);
                        reinaNegra.setFitWidth(80);
                        reinaNegra.setFitHeight(80);
                        b[i][j].setGraphic(reinaNegra);
                        Modelo.reinaN = reinaNegra;
                    }
                    //rey negro
                    if (i == 4) {
                        Image reyN = new Image("/ec/edu/espol/chessg12/imagenes/rey negro.png");

                        ImageView reyNegro = new ImageView(reyN);
                        reyNegro.setFitWidth(80);
                        reyNegro.setFitHeight(80);
                        b[i][j].setGraphic(reyNegro);
                        Modelo.reyN = reyNegro;

                    }

                }

                //color tablero
                {
                    if (i % 2 == 0) {
                        if (j % 2 == 0) {
                            b[i][j].setStyle("-fx-background-color: beige;");

                        } else {
                            b[i][j].setStyle("-fx-background-color: brown;");
                        }
                    } else {
                        if (j % 2 == 0) {
                            b[i][j].setStyle("-fx-background-color: brown;");
                        } else {
                            b[i][j].setStyle("-fx-background-color: beige;");
                        }
                    }
                }

                Tablero.add(b[i][j], i, j);

            }

        }

    }

    public boolean movimientosCaballo(int xInicial, int yInicial, int xFinal, int yFinal) {
        if ((xInicial + 1 == xFinal || xInicial - 1 == xFinal)) {
            if (yInicial == yFinal + 2 || yInicial == yFinal - 2) {
                return true;
            }
        } else if (yInicial + 1 == yFinal || yInicial - 1 == yFinal) {
            if (xInicial == xFinal + 2 || xInicial == xFinal - 2) {
                return true;
            }
        }

        return false;

    }

    public boolean compImagenes(Image image1, Image image2) {
        if (image1 == null || image2 == null) {
            return false;
        }

        return image1.getUrl().equals(image2.getUrl());
    }

    public boolean piezaBlanca(ImageView Pieza) {
        return compImagenes(Pieza.getImage(), Modelo.bishopBlanco.getImage()) || compImagenes(Pieza.getImage(), Modelo.caballoBlanco.getImage())
                || compImagenes(Pieza.getImage(), Modelo.peonB.getImage())
                || compImagenes(Pieza.getImage(), Modelo.torreBlanca.getImage()) || compImagenes(Pieza.getImage(), Modelo.reyB.getImage())
                || compImagenes(Pieza.getImage(), Modelo.reinaB.getImage());

    }

    public boolean movimientosPeon(int xInicial, int yInicial, int xFinal, int yFinal, ImageView PiezaI, ImageView PiezaF) {

        if (movimientoValidoBRT(xInicial, yInicial, xFinal, yFinal)) {

            if (yInicial == yFinal) {

                if (xInicial == 1 || xInicial == 6) {
                    if (piezaBlanca(PiezaI)) {

                        if (xInicial - 2 == xFinal || xInicial - 1 == xFinal) {
                            return true;
                        }
                    } else {
                        if (xInicial + 2 == xFinal || xInicial + 1 == xFinal) {
                            return true;
                        }
                    }
                } else {
                    if (piezaBlanca(PiezaI)) {
                        if (xInicial - 1 == xFinal) {
                            return true;
                        }
                    } else {
                        if (xInicial + 1 == xFinal) {
                            return true;
                        }
                    }
                }

            }
        }

        return false;

    }

    public boolean comerPeon(int xInicial, int yInicial, int xFinal, int yFinal, ImageView PiezaI) {
        if(movimientoValidoBRT(xInicial, yInicial, xFinal, yFinal)){
        if (piezaBlanca(PiezaI)) {

            if (xInicial - 1 == xFinal) {

                if (yInicial != yFinal) {
                    if (yInicial - 1 == yFinal || yInicial + 1 == yFinal) {

                        return true;

                    }
                }
            }

        } else {
            if (xInicial + 1 == xFinal) {
                if (yInicial != yFinal) {
                    if (yInicial - 1 == yFinal || yInicial + 1 == yFinal) {
                        return true;

                    }
                }
            }

        }}

        return false;
    }

    public boolean movimientoTorre(int xInicial, int yInicial, int xFinal, int yFinal) {
        if (movimientoValidoBRT(xInicial, yInicial, xFinal, yFinal)) {
            if (xInicial == xFinal || yInicial == yFinal) {
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean movimientoReina(int xInicial, int yInicial, int xFinal, int yFinal) {
        if (movimientoValidoBRT(xInicial, yInicial, xFinal, yFinal)) {
            if (xInicial == xFinal || yInicial == yFinal) {
                return true;

            }
            int deltaX = Math.abs(xFinal - xInicial);
            int deltaY = Math.abs(yFinal - yInicial);

            if (deltaX == deltaY) {
                return true;

            }
        }
        return false;

    }

    public boolean movimientoBishop(int xInicial, int yInicial, int xFinal, int yFinal) {
        if (movimientoValidoBRT(xInicial, yInicial, xFinal, yFinal)) {

            int deltaX = Math.abs(xFinal - xInicial);
            int deltaY = Math.abs(yFinal - yInicial);

            if (deltaX == deltaY) {
                return true;

            }
        }

        return false;

    }

    public boolean movimientoRey(int xInicial, int yInicial, int xFinal, int yFinal) {

        int deltaX = Math.abs(xFinal - xInicial);
        int deltaY = Math.abs(yFinal - yInicial);

        if ((deltaX <= 1 && deltaY <= 1)) {
            return true;
        }

        return false;

    }

    public boolean esCaballo() {
        return compImagenes(Modelo.caballoBlanco.getImage(), Modelo.imagePiezaI.getImage()) || compImagenes(Modelo.caballoNegro.getImage(), Modelo.imagePiezaI.getImage());
    }

    public boolean esPeon() {
        return compImagenes(Modelo.peonB.getImage(), Modelo.imagePiezaI.getImage()) || compImagenes(Modelo.peonN.getImage(), Modelo.imagePiezaI.getImage());
    }

    public boolean esBishop() {
        return compImagenes(Modelo.bishopBlanco.getImage(), Modelo.imagePiezaI.getImage()) || compImagenes(Modelo.bishopNegro.getImage(), Modelo.imagePiezaI.getImage());
    }

    public boolean esRey() {
        return compImagenes(Modelo.reyB.getImage(), Modelo.imagePiezaI.getImage()) || compImagenes(Modelo.reyN.getImage(), Modelo.imagePiezaI.getImage());
    }

    public boolean esReina() {
        return compImagenes(Modelo.reinaB.getImage(), Modelo.imagePiezaI.getImage()) || compImagenes(Modelo.reinaN.getImage(), Modelo.imagePiezaI.getImage());
    }

    public boolean esTorre() {
        return compImagenes(Modelo.torreBlanca.getImage(), Modelo.imagePiezaI.getImage()) || compImagenes(Modelo.torreNegra.getImage(), Modelo.imagePiezaI.getImage());
    }

    public boolean movimientoValidoBRT(int xInicial, int yInicial, int xFinal, int yFinal) {
        int pasoX = (xFinal - xInicial) > 0 ? 1 : ((xFinal - xInicial) < 0 ? -1 : 0);
        int pasoY = (yFinal - yInicial) > 0 ? 1 : ((yFinal - yInicial) < 0 ? -1 : 0);

        int x = xInicial + pasoX;
        int y = yInicial + pasoY;

        if ((xInicial == xFinal || yInicial == yFinal)) {

            while (x != xFinal || y != yFinal) {
                if (b[y][x].getGraphic() != null) {
                    return false;
                }
                x += pasoX;
                y += pasoY;

            }

        }
        int deltaX = Math.abs(xFinal - xInicial);
        int deltaY = Math.abs(yFinal - yInicial);

        if (deltaX == deltaY) {
            while (x != xFinal && y != yFinal) {
                if (b[y][x].getGraphic() != null) {
                    return false;

                }
                x += pasoX;
                y += pasoY;

            }

        }

        return true;

    }
