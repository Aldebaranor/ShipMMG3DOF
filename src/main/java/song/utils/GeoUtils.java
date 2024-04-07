package song.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.text.DecimalFormat;




//坐标系转换工具类
public final class GeoUtils {

    private static final DecimalFormat DF = new DecimalFormat("0.000000");
    private static double EARTH_RADIUS = 6378.137;
    private static Double INFINITY = 1e10;
    private static Double DELTA = 1e-10;

    private GeoUtils() {
        throw new IllegalStateException("Utils");
    }

    public static double getRadian(double degree) {
        return degree * Math.PI / 180.0;
    }

    public static Point calLocationByDistanceAndLocationAndDirection(double angle, double startLong, double startLat, double distance) {
        //将距离转换成经度的计算公式
        double r = distance / EARTH_RADIUS;
        // 转换为radian，否则结果会不正确
        angle = Math.toRadians(angle);
        startLong = Math.toRadians(startLong);
        startLat = Math.toRadians(startLat);
        double lat = Math.asin(Math.sin(startLat) * Math.cos(r) + Math.cos(startLat) * Math.sin(r) * Math.cos(angle));
        double lon = startLong + Math.atan2(Math.sin(angle) * Math.sin(r) * Math.cos(startLat), Math.cos(r) - Math.sin(startLat) * Math.sin(lat));
        // 转为正常的10进制经纬度
        lon = Math.toDegrees(lon);
        lat = Math.toDegrees(lat);
        Point point = new Point(Double.valueOf(DF.format(lon)), Double.valueOf(DF.format(lat)));
        return point;
    }

    public static double[] millerToXY(double lon,double lat){
        // 经纬度转换为平面坐标系中的x,y，利用米勒投影坐标系
        double L = 6381372 * Math.PI * 2;
        double W = L;
        double H = L / 2;
        double mill = 2.3;

        // 计算转换后的x和y坐标
        double x = lon * Math.PI / 180;
        double y = lat * Math.PI / 180;
        y = 1.25 * Math.log(Math.tan(0.25 * Math.PI + 0.4 * y));
        x = (W / 2) + (W / (2 * Math.PI)) * x;
        y = (H / 2) - (H / (2 * mill)) * y;

        // 返回转换后的坐标，由于Java中没有元组，所以这里使用数组表示(x, y)
        return new double[] {x, -y}; // 需要改为-y，使得旋转方向与经纬度相同
    }

    /*
    # 进行受限于操舵速度能力的舵角调整
    # 目标舵角，当前舵角，每秒调角度，以及时间步长
    # 输出下一步长的调制后舵角
     */
    public static double dertaShouldbe(double dertaDegInput,double dertaDeg,double change,double dt){

        double delta = dertaDegInput - dertaDeg; // 当前舵角度与目标舵角之间的差距
        double changePerDt = Math.abs(change * dt); // 每次时间步长内可行的最大变化量

        // 如果 delta 大于 changePerDt，则每次只改变 changePerDt 的绝对值
        if (Math.abs(delta) > changePerDt) {
            if (delta > 0) {
                dertaDeg += changePerDt;
            } else {
                dertaDeg -= changePerDt;
            }
        }
        // 如果 delta 小于等于 changePerDt，则直接设置为 targetAngleDeg
        else {
            dertaDeg = dertaDegInput;
        }

        return dertaDeg;
    }




    //后期优化封装用，这个python转java好多问题，python写的依托答辩，我还要两天给你看完写完
    //定义工具类的点
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Point implements Serializable {

        private Double x;
        private Double y;

    }

    //定义工具类的线
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Line implements Serializable {

        private Point p1 = new Point();
        private Point p2 = new Point();

    }
}
