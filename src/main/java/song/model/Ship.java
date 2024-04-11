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
    //设定泰勒展开速度
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
    //盘面比
    private Double panmianbi;
    //螺距比
    private Double luojubi;
    //桨的转速
    private Double n;

    //缩尺比
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
    //Hull Transversal Projection Area above Waterline (HTPA) (m2)
    private Double shipAreaT;
    //Hull Longitudinal Projection Area above Waterline (HLPA) (m2)
    private Double shipAreaL;
    //Superstructure Longitudinal Projection Area (SLPA) (m2)
    private Double shipAreaSS;
    //Longitudinal Distance from Stem to Center of HTPA + SLPA (m)
    private Double shipLxAreaLSS;
    //Vertical Distance from CG to Center of HTPA + SLPA (m)
    private Double shipLzAreaLSS;
    //Circumference of HTPA + SLPA excluding Lwl (m)
    private Double shipCirAreaLSS;
    //Number of Mast
    private Double shipPilaN;

    //设置参数
    //仿真时间
    private Double t;
    //油门
    private Double youmen;
    //实际进入计算的舵角
    private Double dertaDeg;
    //设定的舵角
    private Double dertaDegInput;
    //风力计算开关
    private Boolean windSwitch;
    private Double winDirnInpDeg;
    private Double winSpdInp;
    //
    private Boolean curnSwitch;
    //180度减速，0度加速，90度北移，-90度南移
    private Double curnDirnInpDeg;
    //流速不要超过0.5
    private Double curnSpdInp;
    private Boolean waveSwitch;
    //波长
    private Double waveNmda;
    //波高波向，与浪向相同
    private Double waveDirnInpDeg;
    //波高
    private Double waveHeight;
    //是否开启调试绘图
    private Boolean drawingSwitch;
    //打舵是根据轨迹自动控制，还是舵角由人指定
    private Boolean controlSwitch;
    //是真实TCP通信，还是我自己的仿真计算
    private Boolean isRealTCPUDP;
    //是否开始仿真
    private Boolean isStartSimulation;
    //状态量
    //实时的位置
    private Double x;
    private Double y;
    private Double yDraw;
    private Double faiDeg;
    private Double headingDeg;
    //present，当前时刻的已知速度
    private Double u;
    private Double v;
    private Double rRad;
    private Double uAcc;
    private Double vAcc;
    private Double rAccRad;

    //让船稳定跑成直航的时间
    private Double tStable;
    private Double tMax;

    private List<Double> P1;//当前时刻的跟踪路径
    private List<Double> P2;
    private Integer indexNextPoint;

    private Boolean justStart;
    private List<Double> ptsDesiredArray;//数据包中的轨迹点
    private List<Double> vtsDesiredArray;//数据包中的期望航速
    private List<Double> ptsMDesiredArray;//经纬度直接转换的距离
    private List<Double> ptsRelaMDesiredArray;//相对origin的距离
    private Double pingTaiXunHangMoShi;//默认为停止状态
    private List<Double> numOfGoals;//跟踪点的数量默认为0，会自动根据Pts_desired_array调整
    private Double range;//判断船舶进入范围的距离
    private double[] origin;//当前起始的船舶的第一个位置
    //如果未重启，但重新设定了起始点，以下值需要重新初始化
    private Boolean finished;//判断是否到终点，到了会进入StopProcess
    private Boolean justToStop;//判断是否是StopProcess中的第一个周期，第一个周期时需要求均值
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
