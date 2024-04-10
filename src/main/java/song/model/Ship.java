package song.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ship implements Serializable {

    private static final long serialVersionUID = -8951060835255591290L;

    //船体参数
    //船体宽度
    private Double b;
    //浆基座横向布置位置
    private Double bProp;
    //船体长度
    private Double l;
    //吃水深度
    private Double d;
    //海水密度
    private Double rou;
    //空气密度
    private Double rouAir;
    //船舶纵向浮心位置
    private Double xT;
    //重力g
    private Double g;
    //设计排水量
    private Double W;
    //船舶质量
    private Double m;
    //船舶排水体积系数
    private Double Cb;
    //
    private Double u0;
    //船舶初稳性
    private Double gM;
    //船舶阻力系数
    private Double c;
    //横向阻力
    private Double kX;
    //船舶湿面积比
    private Double nMda2;
    //首尾吃水差与吃水比
    private Double tao_;
    //相对旋回直径
    private Double lv;
    //船舶质量矩横轴分量
    private Double mX;
    //纵轴分量
    private Double mY;
    //船舶惯性矩关于垂直轴的分量
    private Double Izz;
    //关于zz轴的转动惯量
    private Double Jzz;
    //船舶质心位置沿船长方向的坐标
    private Double xG;

    //主机参数
    private Double fullPower;
    //拟定减速比
    private Double jianSu;

    //桨的参数
    private Double dPod;
    //每秒可调整角度
    private Double change;
    private Double panmianbi;
    private Double luojubi;
    //桨的转速
    private Double n;

    //2024.04.10 20:02新增参数
    //没有注释
    private Double suochibi;

    //舵的参数
    private Double ARRud;
    private Double miuR;
    private Double bRud;
    private Double hRud;
    private Double nmdaRud;

    private Double xR;
    private Double yPRL;
    private Double yPRR;

    //计算风力相关参数
    //干弦
    private Double tGanxian;
    //上层建筑
    private Double tSuperStru;
    private Double shipAreaT;
    private Double shipAreaL;
    private Double shipAreaSS;
    private Double shipLxAreaLSS;
    private Double shipLzAreaLSS;
    private Double shipCirAreaLSS;
    private Double shipPilaN;

    //设置参数
    private Double t;//仿真时间？
    private Double youmen;
    private Double dertaDeg;
    private Double dertaDegInput;
    private Boolean windSwitch;
    private Double winDirnInpDeg;
    private Double winSpdInp;
    private Boolean curnSwitch;
    private Double curnDirnInpDeg;
    private Double curnSpdInp;
    private Boolean waveSwitch;
    private Double waveNmda;
    private Double waveDirnInpDeg;
    private Double waveHeight;
    private Boolean drawingSwitch;
    private Boolean controlSwitch;
    private Boolean isRealTCPUDP;
    private Boolean isStartSimulation;
    private Double x;
    private Double y;
    private Double yDraw;
    private Double faiDeg;
    private Double headingDeg;
    private Double u;
    private Double v;
    private Double rRad;
    private Double uAcc;
    private Double vAcc;
    private Double rAccRad;

    private Double tStable;
    private Double tMax;

    private List<Double> P1;
    private List<Double> P2;
    private Integer indexNextPoint;

    private Boolean justStart;
    private List<Double> ptsDesiredArray;
    private List<Double> vtsDesiredArray;
    private List<Double> ptsMDesiredArray;
    private List<Double> ptsRelaMDesiredArray;
    private Double pingTaiXunHangMoShi;
    private List<Double> numOfGoals;
    private Double range;
    private double[] origin;
    private Boolean finished;
    private Boolean justToStop;
    private Boolean stopSuccess;
    //绘图用的参数
    private List<Double> tArray;
    private List<Double> qiuArray;
    private List<Double> XpArray;
    private List<Double> YpArray;
    private List<Double> NpArray;
    private List<Double> XRArray;
    private List<Double> YRArray;
    private List<Double> NRArray;
    private List<Double> XHArray;
    private List<Double> YHArray;
    private List<Double> NHArray;
    private List<Double> XwdArray;
    private List<Double> YwdArray;
    private List<Double> NwdArray;
    private List<Double> XwvArray;
    private List<Double> YwvArray;
    private List<Double> NwvArray;
    private List<Double> uArray;
    private List<Double> vArray;
    private List<Double> urArray;
    private List<Double> vrArray;
    private List<Double> V_Array;
    private List<Double> rDegArray;
    private List<Double> rRadArray;
    private List<Double> xArray;
    private List<Double> yArray;
    private List<Double> yDrawArray;
    private List<Double> FaiDegArray;
    private List<Double> FaiDegDrawArray;
    private List<Double> headingArray;
    private List<Double> piaoJiaoArray;
    private List<Double> piaoJiaoArrayDeg;
    private List<Double> nArray;
    private List<Double> dertaDegArray;
    private List<Double> winDirnEncDegArray;
    private List<Double> winSpdEncArray;
    private List<Double> winThetaEncArray;
    private List<Double> xLeftShortArray;
    private List<Double> yLeftShortArray;
    private List<Double> nLeftShortArray;
    private List<Double> xLeftLongArray;
    private List<Double> yLeftLongArray;
    private List<Double> nLeftLongArray;
    private List<Double> uAccArray;
    private List<Double> vAccArray;
    private List<Double> rAccRadArray;
    private List<Double> Cxw0Array;
    private List<Double> Cxw1Array;
    private List<Double> Cxw2Array;
    private List<Double> Cyw0Array;
    private List<Double> Cyw1Array;
    private List<Double> Cyw2Array;
    private List<Double> Cyw3Array;
    private List<Double> Cyw4Array;
    private List<Double> Cnw0Array;
    private List<Double> Cnw1Array;
    private List<Double> Cnw2Array;
    private List<Double> Cnw3Array;
    private List<Double> Cnw4Array;
    private List<Double> eFaiRadRecordArray;



    //计算船体参数get方法
    public void setW() {
        this.W = 3495/(Math.pow(suochibi,3)) * rou;
    }

    public void setM() {
        this.m = W;
    }

    public void setCb() {
        this.Cb = W/(l*b*d)/rou;
    }

    public void setC() {
        this.c = 0.3085+0.0227*b/d-0.0043*l/100;
    }

    public void setKX() {
        this.kX = getC()*b;
    }

    public void setNMda2() {
        this.nMda2 = 2*d/l;
    }

    public void setTao_() {
        this.tao_ = -0.2/d;
    }

    public void setLv() {
        this.lv = getNMda2()/(3.14/2*getNMda2()+1.4*getCb()*b/l);
    }

    public void setJzz() {
        this.Jzz = getM()/100*l*l*(33-76.85*getCb()*(1-0.784*getCb())+3.43*(1-0.63*getCb())*l/b);
    }

    public void setXG(){
        this.xG = -0.05 * l;
    }

    public void setDPod(){
        this.dPod = 3.8 / suochibi;
    }

    public void setARRud(){
        this.ARRud = 7.29 /(suochibi*suochibi);
    }

    public void setBRud(){
        this.bRud = 2.229/suochibi;
    }

    public void setHRud(){
        this.hRud = 3.27/suochibi;
    }

    public void setXR(){
        this.xR = - l/2 *0.95;
    }

    public void setYPRL(){
        this.yPRL = -b/4;
    }

    public void setYPRR(){
        this.yPRR = b/4;
    }


    //计算风力参数get方法

    public void setShipAreaT() {
        this.shipAreaT = 1*tGanxian;
    }

    public void setShipAreaL() {
        this.shipAreaL = l*tGanxian;
    }

    public void setShipAreaSS() {
        this.shipAreaSS = l*tSuperStru;
    }

    public void setShipLxAreaLSS() {
        this.shipLxAreaLSS = l/2;
    }

    public void setShipLzAreaLSS() {
        this.shipLzAreaLSS = Double.valueOf(0);
    }

    public void setShipCirAreaLSS() {
        this.shipCirAreaLSS = l*1.6;
    }

    public void setShipPilaN() {
        this.shipPilaN = Double.valueOf(0);
    }
}
