package song.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class EllipsoidParameters {
    private double a;
    private double e2;
    private double b;
    private double ep2;
    private double f;
    private double c;
    private double a0;
    private double a2;
    private double a4;
    private double a6;

    public EllipsoidParameters() {
        // Default: wgs84
        //todo:改成外部输入
        this.a = 6378137.0;
        this.e2 = 0.00669437999013;
        this.b = Math.sqrt(this.a * this.a * (1 - this.e2));
        this.ep2 = (this.a * this.a - this.b * this.b) / (this.b * this.b);
        this.f = (this.a - this.b) / this.a;
        this.c = this.a / (1 - this.f);
        double[] mValues = calculateMValues();
        this.a0 = mValues[0] + mValues[1] / 2 + 3 * mValues[2] / 8 + 5 * mValues[3] / 16 + 35 * mValues[4] / 128;
        this.a2 = mValues[1] / 2 + mValues[2] / 2 + 15 * mValues[3] / 32 + 7 * mValues[4] / 16;
        this.a4 = mValues[2] / 8 + 3 * mValues[3] / 16 + 7 * mValues[4] / 32;
        this.a6 = mValues[3] / 32 + mValues[4] / 16;
    }

    private double[] calculateMValues() {
        double m0 = this.a * (1 - this.e2);
        double m2 = 1.5 * this.e2 * m0;
        double m4 = 1.25 * this.e2 * m2;
        double m6 = 7 * this.e2 * m4 / 6;
        double m8 = 9 * this.e2 * m6 / 8;
        return new double[]{m0, m2, m4, m6, m8};
    }
}
