package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Main extends Application {

    private Canvas canvas = new Canvas(300, 250);
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
    private Group root = new Group();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        // drawBasicShapes
        drawShapes(graphicsContext);

        moveCanvas(0, 0);
        drawDShape(); // 绘制贝塞尔曲线
        drawRadialGradient(Color.RED, Color.YELLOW);
        drawLinearGradient(Color.BLUE, Color.GREEN);
        drawDropShadow(Color.GRAY, Color.BLUE, Color.GREEN, Color.RED);

        // 添加事件处理:在用户拖动鼠标时擦除部分内容
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        graphicsContext.clearRect(e.getX() - 2, e.getY() - 2, 5, 5);
                    }
                });

        root.getChildren().add(canvas);
        primaryStage.setTitle("Drawing on Canvas");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * 绘制基本形状
     * @param graphicsContext
     */
    private void drawShapes(GraphicsContext graphicsContext) {
        // 设置填充色
        graphicsContext.setFill(Color.GREEN);
        // 设置边
        graphicsContext.setStroke(Color.BLUE);
        // 设置画笔宽度
        graphicsContext.setLineWidth(5);
        // 画直线
        graphicsContext.strokeLine(40, 10, 10, 40);
        // 画填充的圆
        graphicsContext.fillOval(10, 60, 30, 30);
        // 画空心圆
        graphicsContext.strokeOval(60, 60, 30, 30);
        // 画填充的圆角矩形
        graphicsContext.fillRoundRect(110, 60, 30, 30, 10, 10);
        // 画空心的圆角矩形
        graphicsContext.strokeRoundRect(160, 60, 30, 30, 10, 10);
        // 画填充的扇形圆弧
        graphicsContext.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        graphicsContext.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        graphicsContext.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        // 画空心的扇形圆弧
        graphicsContext.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        graphicsContext.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        graphicsContext.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        // 画多边形
        graphicsContext.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        graphicsContext.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        graphicsContext.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);
    }

    // 可以移动到0， 0
    private void moveCanvas(int x, int y) {
        canvas.setTranslateX(x);
        canvas.setTranslateY(y);
    }

    // 绘制贝塞尔曲线
    private void drawDShape() {
        graphicsContext.beginPath();
        graphicsContext.moveTo(50, 50);
        graphicsContext.bezierCurveTo(150, 20, 150, 150, 75, 150);
        graphicsContext.closePath();
    }

    private void drawRadialGradient(Color firstColor, Color lastColor) {
        graphicsContext.setFill(new RadialGradient(0, 0, 0.5, 0.5, 0.1, true,
                CycleMethod.REFLECT,
                new Stop(0.0, firstColor),
                new Stop(1.0, lastColor)));
        graphicsContext.fill();
    }

    private void drawLinearGradient(Color firstColor, Color secondColor) {
        LinearGradient lg = new LinearGradient(0, 0, 1, 1, true,
                CycleMethod.REFLECT,
                new Stop(0.0, firstColor),
                new Stop(1.0, secondColor));
        graphicsContext.setStroke(lg);
        graphicsContext.setLineWidth(20);
        graphicsContext.stroke();
    }

    private void drawDropShadow(Color firstColor, Color secondColor,
                                Color thirdColor, Color fourthColor) {
        graphicsContext.applyEffect(new DropShadow(20, 20, 0, firstColor));
        graphicsContext.applyEffect(new DropShadow(20, 0, 20, secondColor));
        graphicsContext.applyEffect(new DropShadow(20, -20, 0, thirdColor));
        graphicsContext.applyEffect(new DropShadow(20, 0, -20, fourthColor));
    }

}
