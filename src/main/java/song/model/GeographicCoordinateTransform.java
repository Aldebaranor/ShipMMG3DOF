package song.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class GeographicCoordinateTransform {
    private double meridianLine;
    private String projType = "g";  // Default projection type


    //全是魔法值我测你的码

    public GeographicCoordinateTransform() {
        this.meridianLine = -360;
    }

    public void setMeridianLine(double lon) {
        if (this.meridianLine < -180) {
            this.meridianLine = Math.floor((lon + 1.5) / 3) * 3;
        }
    }

    public double[] bl2xy(double lat, double lon, double[] xy) {
        if (this.meridianLine < -180) {
            setMeridianLine(lon);
        }

        double radLat = Math.toRadians(lat);
        double dL = Math.toRadians(lon - this.meridianLine);

        double X = ellipsoidParams.getA0() * radLat - ellipsoidParams.getA2() * Math.sin(2 * radLat) / 2 + ellipsoidParams.getA4() * Math.sin(4 * radLat) / 4 - ellipsoidParams.getA6() * Math.sin(6 * radLat) / 6;
        double tn = Math.tan(radLat);
        double tn2 = tn * tn;
        double tn4 = tn2 * tn2;

        double j2 = ((1 / Math.pow(1 - ellipsoidParams.getF(), 2)) - 1) * Math.pow(Math.cos(radLat), 2);
        double n = ellipsoidParams.getA() / Math.sqrt(1.0 - ellipsoidParams.getE2() * Math.pow(Math.sin(radLat), 2));

        double[] temp = new double[6];
        temp[0] = n * Math.sin(radLat) * Math.cos(radLat) * Math.pow(dL, 2) / 2;
        temp[1] = n * Math.sin(radLat) * Math.pow(Math.cos(radLat), 3) * (5 - tn2 + 9 * j2 + 4 * Math.pow(j2, 2)) * Math.pow(dL, 4) / 24;
        temp[2] = n * Math.sin(radLat) * Math.pow(Math.cos(radLat), 5) * (61 - 58 * tn2 + tn4) * Math.pow(dL, 6) / 720;
        temp[3] = n * Math.cos(radLat) * dL;
        temp[4] = n * Math.pow(Math.cos(radLat), 3) * (1 - tn2 + j2) * Math.pow(dL, 3) / 6;
        temp[5] = n * Math.pow(Math.cos(radLat), 5) * (5 - 18 * tn2 + tn4 + 14 * j2 - 58 * tn2 * j2) * Math.pow(dL, 5) / 120;

        xy[0] = X + temp[0] + temp[1] + temp[2];
        xy[1] = temp[3] + temp[4] + temp[5];

        if (this.projType.equals("g")) {
            xy[0] += 500000;
        } else if (this.projType.equals("u")) {
            xy[0] = xy[0] * 0.9996 + 500000;
            xy[1] = xy[1] * 0.9996;
        }

        return xy;
    }

    // 创建椭球参数实例
    private static final EllipsoidParameters ellipsoidParams = new EllipsoidParameters();

    // 创建坐标转换实例
    public static final GeographicCoordinateTransform coordTransform = new GeographicCoordinateTransform();
}
