package song.service.Impl;

import org.springframework.stereotype.Service;
import song.model.*;
import song.service.motionService;
import org.apache.commons.math3.util.FastMath;

import java.util.ArrayList;
import java.util.List;

import static song.model.Contents.dt;

@Service
public class motionServiceImpl implements motionService {

    Ship ship = new Ship();
    //todo:从外部输入参数

    //设置参数
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
    private Boolean drowingSwitch;
    private Boolean controlSwitch;
    private Boolean isRealTCPUDP;
    private Boolean isStartSimulation;
    //不是，x,y,u,v这种变量名你敢随便取的？？？？
//    private Double x;
//    private Double y;
    private Double yDraw;
    private Double faiDeg;
    private Double headingDeg;
//    private Double u;
//    private Double v;
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
    //todo:参数设置
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
    private List<Double> VArray;
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
    private double X0, Xu, X_uu, X_u_De, X_vv, X_rr, X_vr;
    private double Y_v, Y_vvv, Y_v_De, Y_r, Y_rrr, Y_vvr, Y_vrr;
    private double N_v, N_vvv, N_r, N_rrr, N_r_De, N_vvr, N_vrr;
    private double[] w1 = {-0.487293, -0.32, 0.06, -0.00438, -3.1186, -0.40, -0.0311};
    private double[] w2 = {-20.887054, 55.168673, -0.238010, -1.326375, 0.054549, -3.853117, 0.147229};
    private double[] w3 = {-18.293072, -12.258180, -9.280442, 0.226242, 0.137077, -0.035439, -0.289610};

    @Override
    public Ship init(){
//        //船体属性
//        ship.setL();
//        ship.setB();
//        ship.setD();
//        ship.setRou();
//        ship.setRouAir();
//        ship.setG();
//        ship.setW();
//        ship.setM();
//        ship.setCb();
//        ship.setC();
//        ship.setKX();
//        ship.setNMda2();
//        ship.setTao_();
//        ship.setLv();
//        ship.setXG();
//        ship.setMX();
//        ship.setMY();
//        ship.setIzz();
//        ship.setJzz();
//        //桨属性
//        ship.setDPod();
//        ship.setChange();
        //船体属性
        ship.setL(10.0);
        ship.setB(3.5);
        ship.setD(0.71);
        ship.setRou(1000.0);
        ship.setRouAir(1.29);
        ship.setG(9.8);
        ship.setW();
        ship.setM();
        ship.setCb();
//        ship.setU0();
        ship.setC();
        ship.setKX();
        ship.setNMda2();
        ship.setTao_();
        ship.setLv();
        ship.setXG(-1.0);
        ship.setMX(0.0);
        ship.setMY(0.0);
        ship.setIzz(0.0);
        ship.setJzz();
        //桨属性
        ship.setDPod(0.46);
        ship.setChange(5.0);
        ship.setTGanxian(2.0);
        ship.setTSuperStru(1.0);
        ship.setShipAreaT();
        ship.setShipAreaL();
        ship.setShipAreaSS();
        ship.setShipLxAreaLSS();
        ship.setShipLzAreaLSS();
        ship.setShipCirAreaLSS();
        ship.setShipPilaN();

        X0 = w1[0] / 10.0;
        Xu = w1[1] / 10.0;
        X_uu = w1[2] / 10.0;
        X_u_De = w1[3] / 10.0;
        X_vv = w1[4] / 10.0;
        X_rr = w1[5] / 10.0;
        X_vr = w1[6] / 10.0;

        Y_v = w2[0] / 100.0;
        Y_vvv = w2[1] / 100.0;
        Y_v_De = w2[2] / 100.0;
        Y_r = w2[3] / 100.0;
        Y_rrr = w2[4] / 100.0;
        Y_vvr = w2[5] / 100.0;
        Y_vrr = w2[6] / 100.0;

        N_v = w3[0] / 100.0;
        N_vvv = w3[1] / 100.0;
        N_r = w3[2] / 100.0;
        N_rrr = w3[3] / 100.0;
        N_r_De = w3[4] / 100.0;
        N_vvr = w3[5] / 100.0;
        N_vrr = w3[6] / 100.0;

        return ship;
    }

    //输入船舶对流速度，转速，螺旋桨转速
    //输出船舶螺旋桨三个力和舵的三个力，由于舵用到一些桨的数据，因此放一起
    //本函数缺参数
    public Void doublePropRudderChange(double ur,double vr,double rRad,double n,double dertaDeg) {
        double shipSpd = Math.sqrt(ur * ur + vr * vr);
        if (shipSpd == 0.0) {
            shipSpd = 0.1;
        }
        double shipBeta = Math.asin(-vr / shipSpd);
        double dertaRad = dertaDeg * Math.PI / 180;

        double mTp = ship.getCb() * 0.5 - 0.18;//3-4-53
        if (mTp < 0) {
            mTp = 0;
        }
        double wp0 = ship.getCb() * 0.55 + 0.2;//3-4-24  wp0 即code中的Wake_P  wp为斜流影响的半流分数，但我不考虑斜流
        double uProp = (1.0 - wp0) * ur;//螺旋桨进速系数
        double J = uProp / (n * ship.getDPod());//进速系数，左右相同

        double KT = -0.03708 * Math.pow(J, 3) + 0.06702 * Math.pow(J, 2) - 0.5618 * J + 0.7184;//左右相同
        //3-4-66
        double Tl = (1 - mTp) * ship.getRou() * Math.pow(n, 2) * Math.pow(ship.getDPod(), 4) * KT;
        double Tr = (1 - mTp) * ship.getRou() * Math.pow(n, 2) * Math.pow(ship.getDPod(), 4) * KT;

        double xP = Tl + Tr;
        double yP = 0;
//        double nP = -Tl * ship.getYPRL() - Tr*ship.getYPRR(); yprl和yrrr为舵参数没有

        //计算舵力部分
        double epsilon = 0.909;//3-5-35 epsilon = (1 - w_R0)/(1 - w_p0)  舵除有效来流相对桨除比值
        double mTr=0.378;
        double mAh=0.262;
        double mXh = -0.433;
        double xH = mXh*ship.getL();
//        double mEta = ship.getDPod()/ship.getHRud();getHRud为舵参数没有
        double mGamma = 0.9;//3-5-114,整流系数，采用code的值
        double mLr = -0.95;//3-5-109
//        double mFa = (6.13*ship.getNMdaRud())/(ship.getNMdaRud()+2.25);//3-5-11,nmda_Rud为舵参数，没有
        double Kai = 0.6/epsilon;
//        double s = 1.0-uProp/(n*ship.getluojubi*ship.getDPod());//luojubi为桨参数，没有

        double alfaR = dertaRad - mGamma*(shipBeta-mLr*rRad*ship.getL()/shipSpd);
        //以下参数均需要舵和桨参数，都没有
//        double FN = -0.5*ship.getRou()*ship.getARRud()*mFa*Math.pow()
//        XR = (1 - m_tR) * FN * math.sin(Derta_rad) * 2            # 3-5-122
//        YR = (1 + m_aH) * FN * math.cos(Derta_rad) * 2
//        NR_l = (self.xR + m_aH * xH) * FN * math.cos(Derta_rad) + XR * self.yPR_l
//        NR_r = (self.xR + m_aH * xH) * FN * math.cos(Derta_rad) + XR * self.yPR_r
//        NR = NR_l + NR_r
//
//
//        return  XP, YP, NP, XR, YR, NR
        return null;
    }

    //用于检查是否跨越30的整数倍的辅助函数
    public Boolean isCrossingMultipleOf30(double prevValue,double currentValue){
        double prevMultiple = Math.floor(prevValue/30);
        double currentMultiple = Math.floor(currentValue/30);
        return currentMultiple!=prevMultiple;
    }

    /*输入船舶风向，风速
    # 风向Win_Dirn_inp为绝对风向，与全局坐标系相同；与船舶坐标系想减，得到相对坐标系，
    # 输出船舶收到的三个方向风力
    # 从一系列数据点x,y经纬度坐标进行转换，得到以m为单位的坐标；需要用到初始化的坐标点，得到相对的m为单位的距离
    # juststart的判断，即使中间修改点轨迹，只要origin定了，也不用修改juststart;即，只要程序没关闭，都只有一个origin，就是船的初始位置
    # 输出为轨迹坐标m为单位，全局起始位置，以及轨迹相对起始位置的m为单位的坐标
     */
    public LonsLatsTrans lonsLatsTrans(List<Double[]> ptsDesiredArray,Boolean justStart){

        LonsLatsTrans lonsLatsTrans = new LonsLatsTrans();

        List<double[]> ptsMDesiredArray = new ArrayList<>();
        List<double[]> ptsRelaMDesiredArray = new ArrayList<>();

        GeographicCoordinateTransform g = new GeographicCoordinateTransform();

        for(Double[] pt:ptsDesiredArray){
            double lon = pt[0];
            double lat = pt[1];
            double[] xy = g.bl2xy(lon,lat, new double[]{0.0, 0.0});
            ptsMDesiredArray.add(xy);
        }

        if(justStart){
            origin = (ptsMDesiredArray.get(0));
        }

        for(double[] xyCoord : ptsMDesiredArray) {
            double[] xRelaYRela = {xyCoord[0] - origin[0], xyCoord[1] - origin[1]};
            ptsRelaMDesiredArray.add(xRelaYRela);
        }

        lonsLatsTrans.setOrigin(origin);
        lonsLatsTrans.setPtsMDesiredArray(ptsMDesiredArray);
        lonsLatsTrans.setPtsRelaMDesiredArray(ptsRelaMDesiredArray);
        return lonsLatsTrans;
    }

    public WindForce windForce(double u,double v,double faiDeg,double winDirnInpDeg,double winSpdInp){
        WindForce windForce = new WindForce();

        double shipLoa = ship.getL();
        double shipBoa= ship.getB();
        double rhoAir = ship.getRouAir();
        double shipAreaT = ship.getShipAreaT();
        double shipAreaL = ship.getShipAreaL();
        double shipAreaSS = ship.getShipAreaSS();
        double shipLxAreaLSS = ship.getShipLxAreaLSS();
        double shipLzAreaLSS = ship.getShipLzAreaLSS();
        double shipCirAreaLSS = ship.getShipCirAreaLSS();
        double shipPilaN = ship.getShipPilaN();

        double def2Rad = Math.PI/180.0;

        //船首向-风向，为遭遇风向,坐标系见无边记笔记
        double winDirnEncDEG = faiDeg + winDirnInpDeg;
        if(winDirnEncDEG >180){
            winDirnEncDEG = winDirnEncDEG - 360;
        }else if(winDirnEncDEG<-180){
            winDirnEncDEG = winDirnEncDEG +360;
        }

        //遭遇风向在-pi到pi之间
        double winDirnEnc = winDirnEncDEG / 180 * Math.PI;
        if(winDirnEnc > Math.PI){
            winDirnEnc = winDirnEnc - 2*Math.PI;
        }else if(winDirnEnc<-Math.PI) {
            winDirnEnc = winDirnEnc + 2 * Math.PI;
        }

        double winSpdEnc = Math.sqrt(Math.pow((winSpdInp * Math.cos(winDirnEnc) - u),2)+Math.pow((winSpdInp*Math.sin(winDirnEnc)+v),2));

        //# Create Isherwood database # Isherwood数据库，0到180度，10度一档
        double[] winDirn = new double[19];
//        double[][] A = new double[19][7];
//        double[][] B = new double[19][7];
//        double[][] C = new double[19][7];

        // ... 这里填充Isherwood数据库的具体数值 ...又是魔法值
        double[][] A = {{2.152, -5.000, 0.243, -0.164, 0.000, 0.000, 0.000},
                {1.714, -3.330, 0.145, -0.121, 0.000, 0.000, 0.000},
                {1.818, -3.970, 0.211, -0.143, 0.000, 0.000, 0.033},
                {1.965, -4.810, 0.243, -0.154, 0.000, 0.000, 0.041},
                {2.323, -5.990, 0.247, -0.190, 0.000, 0.000, 0.042},
                {1.726, -6.540, 0.189, -0.173, 0.348, 0.000, 0.048},
                {0.913, -4.680, 0.000, -0.104, 0.482, 0.000, 0.052},
                {0.457, -2.880, 0.000, -0.068, 0.346, 0.000, 0.043},
                {0.341, -0.910, 0.000, -0.031, 0.000, 0.000, 0.032},
                {0.355, 0.000, 0.000, 0.000, -0.247, 0.000, 0.018},
                {0.601, 0.000, 0.000, 0.000, -0.372, 0.000, -0.020},
                {0.651, 1.290, 0.000, 0.000, -0.582, 0.000, -0.031},
                {0.564, 2.540, 0.000, 0.000, -0.748, 0.000, -0.024},
                {-0.142, 3.580, 0.000, 0.047, -0.700, 0.000, -0.028},
                {-0.677, 3.640, 0.000, 0.069, -0.529, 0.000, -0.032},
                {-0.723, 3.140, 0.000, 0.064, -0.475, 0.000, -0.032},
                {-2.148, 2.560, 0.000, 0.081, 0.000, 1.270, -0.027},
                {-2.707, 3.970, -0.175, 0.126, 0.000, 1.810, 0.000},
                {-2.529, 3.760, -0.174, 0.128, 0.000, 1.550, 0.000}
        };

        double[][] B = {{0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000},
                {0.096, 0.220, 0.000, 0.000, 0.000, 0.000, 0.000},
                {0.176, 0.710, 0.000, 0.000, 0.000, 0.000, 0.000},
                {0.225, 1.380, 0.000, 0.023, 0.000, -0.290, 0.000},
                {0.329, 1.820, 0.000, 0.043, 0.000, -0.590, 0.000},
                {1.164, 1.260, 0.121, 0.000, -0.242, -0.950, 0.000},
                {1.163, 0.960, 0.101, 0.000, -0.177, -0.880, 0.000},
                {0.916, 0.530, 0.069, 0.000, 0.000, -0.650, 0.000},
                {0.844, 0.550, 0.082, 0.000, 0.000, -0.540, 0.000},
                {0.889, 0.000, 0.138, 0.000, 0.000, -0.660, 0.000},
                {0.799, 0.000, 0.155, 0.000, 0.000, -0.550, 0.000},
                {0.797, 0.000, 0.151, 0.000, 0.000, -0.550, 0.000},
                {0.996, 0.000, 0.184, 0.000, -0.212, -0.660, 0.340},
                {1.014, 0.000, 0.191, 0.000, -0.280, -0.690, 0.440},
                {0.784, 0.000, 0.166, 0.000, -0.209, -0.530, 0.380},
                {0.536, 0.000, 0.176, -0.029, -0.163, 0.000, 0.270},
                {0.251, 0.000, 0.106, -0.022, 0.000, 0.000, 0.000},
                {0.125, 0.000, 0.046, -0.012, 0.000, 0.000, 0.000},
                {0.000, 0.000, 0.000, 0.000, 0.000, 0.000, 0.000}
        };

        double[][] C = {{0.0000, 0.000, 0.0000, 0.0000, 0.0000, 0.000, 0.000},
                {0.0596, 0.061, 0.0000, 0.0000, 0.0000, -0.074, 0.000},
                {0.1106, 0.204, 0.0000, 0.0000, 0.0000, -0.170, 0.000},
                {0.2258, 0.245, 0.0000, 0.0000, 0.0000, -0.380, 0.000},
                {0.2017, 0.457, 0.0000, 0.0067, 0.0000, -0.472, 0.000},
                {0.1759, 0.573, 0.0000, 0.0118, 0.0000, -0.523, 0.000},
                {0.1925, 0.480, 0.0000, 0.0115, 0.0000, -0.546, 0.000},
                {0.2133, 0.315, 0.0000, 0.0081, 0.0000, -0.526, 0.000},
                {0.1827, 0.254, 0.0000, 0.0053, 0.0000, -0.443, 0.000},
                {0.2627, 0.000, 0.0000, 0.0000, 0.0000, -0.508, 0.000},
                {0.2102, 0.000, -0.0195, 0.0000, 0.0335, -0.492, 0.000},
                {0.1567, 0.000, -0.0258, 0.0000, 0.0497, -0.457, 0.000},
                {0.0801, 0.000, -0.0311, 0.0000, 0.0740, -0.396, 0.000},
                {0.0189, 0.000, -0.0488, 0.0101, 0.1128, -0.420, 0.000},
                {0.0256, 0.000, -0.0422, 0.0100, 0.0889, -0.463, 0.000},
                {0.0552, 0.000, -0.0381, 0.0109, 0.0689, -0.476, 0.000},
                {0.0881, 0.000, -0.0306, 0.0091, 0.0366, -0.415, 0.000},
                {0.0851, 0.000, -0.0122, 0.0025, 0.0000, -0.220, 0.000},
                {0.0000, 0.000, 0.0000, 0.0000, 0.0000, 0.000, 0.000}
        };

        // 查找风向区域
        double winThetaEnc;
        if (winDirnEnc > 0) {
            winThetaEnc = -winDirnEnc + Math.PI;
        } else {
            winThetaEnc = winDirnEnc + Math.PI;
        }

        int i1_dirn = 0;
        int i2_dirn = 0;
        for (int i = 0; i < 19; i++) {
            double temp = Math.abs(winThetaEnc - winDirn[i]);
            if (temp < 1e-6) {
                i1_dirn = i;
                i2_dirn = i;
                break;
            }
        }
        if (i1_dirn == 0) {
            for (int i = 0; i < 18; i++) {
                if (winDirn[i] < winThetaEnc && winDirn[i + 1] > winThetaEnc) {
                    i1_dirn = i;
                    i2_dirn = i + 1;
                    break;
                }
            }
        }

        // 插值遭遇风负载系数
        double[] A_enc = new double[7];
        double[] B_enc = new double[7];
        double[] C_enc = new double[7];
        if (i1_dirn == i2_dirn) {
            for (int j = 0; j < 7; j++) {
                A_enc[j] = A[i1_dirn][j];
                B_enc[j] = B[i1_dirn][j];
                C_enc[j] = C[i1_dirn][j];
            }
        } else {
            double temp1 = Math.abs(winThetaEnc - winDirn[i1_dirn]);
            double temp2 = Math.abs(winThetaEnc - winDirn[i2_dirn]);
            for (int j = 0; j < 7; j++) {
                A_enc[j] = (A[i1_dirn][j] * temp2 + A[i2_dirn][j] * temp1) / (temp1 + temp2);
                B_enc[j] = (B[i1_dirn][j] * temp2 + B[i2_dirn][j] * temp1) / (temp1 + temp2);
                C_enc[j] = (C[i1_dirn][j] * temp2 + C[i2_dirn][j] * temp1) / (temp1 + temp2);
            }
        }

        // 计算风力
        double Cxa = A_enc[0] + A_enc[1] * 2 * shipAreaL / Math.pow(shipLoa, 2) + A_enc[2] * 2 * shipAreaT / Math.pow(shipBoa, 2) + A_enc[3] * shipLoa / shipBoa + A_enc[4] * shipCirAreaLSS / shipLoa + A_enc[5] * shipLxAreaLSS / shipLoa + A_enc[6] * shipPilaN;
        double Cya = B_enc[0] + B_enc[1] * 2 * shipAreaL / Math.pow(shipLoa, 2) + B_enc[2] * 2 * shipAreaT / Math.pow(shipBoa, 2) + B_enc[3] * shipLoa / shipBoa + B_enc[4] * shipCirAreaLSS / shipLoa + B_enc[5] * shipLxAreaLSS / shipLoa + B_enc[6] * shipAreaSS / shipAreaL;
        double Cna = C_enc[0] + C_enc[1] * 2 * shipAreaL / Math.pow(shipLoa, 2) + C_enc[2] * 2 * shipAreaT / Math.pow(shipBoa, 2) + C_enc[3] * shipLoa / shipBoa + C_enc[4] * shipCirAreaLSS / shipLoa + C_enc[5] * shipLxAreaLSS / shipLoa;

        double[] Win_Foc = new double[6];
        Win_Foc[0] = -0.5 * rhoAir * Math.pow(winSpdEnc, 2) * shipAreaT * Cxa;
        Win_Foc[1] = Math.signum(-winDirnEnc) * 0.5 * rhoAir * Math.pow(winSpdEnc, 2) * shipAreaL * Cya;
        Win_Foc[2] = 0.0;
        Win_Foc[3] = Win_Foc[1] * shipLzAreaLSS;
        Win_Foc[4] = 0.0;
        Win_Foc[5] = Math.signum(-winDirnEnc) * 0.5 * rhoAir * Math.pow(winSpdEnc, 2) * shipAreaL * shipLoa * Cna;

        double Xwd = Win_Foc[0];
        double Ywd = Win_Foc[1] / 2;
        double Nwd = Win_Foc[5] / 2;

        windForce.setXwd(Xwd);
        windForce.setYwd(Ywd);
        windForce.setNwd(Nwd);
        windForce.setWinDirnEncDeg(winDirnEncDEG);
        windForce.setWinSpdEnc(winSpdEnc);
        windForce.setWinThetaEnc(winThetaEnc);
        return windForce;
    }

    //current_relative
    public CurrentRelative currentRelative(double u,double v,double faiDeg,double curnDirnInpDeg,double curnSpdInp){
        CurrentRelative currentRelative = new CurrentRelative();
        double relativeCurnDirnDeg = curnDirnInpDeg - faiDeg;
        double relativeCurnDirnRad = relativeCurnDirnDeg * Math.PI /180;
        double uc = curnSpdInp *Math.cos(relativeCurnDirnRad);
        double vc = curnSpdInp *Math.sin(relativeCurnDirnRad);
        double ur = u-uc;
        double vr = v-vc;
        currentRelative.setUc(uc);
        currentRelative.setVc(vc);
        currentRelative.setUr(ur);
        currentRelative.setVr(vr);
        return currentRelative;
    }

    //流来向的定义与浪向相同；由于没有准确的系数，此方式废除
    //current_force

    //计算波浪漂移力  nmda为波长，单位m, Wave_Dirn_inp_deg为浪的绝对方向，与风向定义相同，Wave_height为波高，是峰谷之差
    public WaveForce waveForce(double faiDeg,double waveNmda,double waveDirnInpDeg,double waveHeight){
        WaveForce waveForce = new WaveForce();
        double waveDirnEncDeg = faiDeg + waveDirnInpDeg;
        if(waveDirnEncDeg > 180){
            waveDirnEncDeg = waveDirnEncDeg - 360;
        }else if(waveDirnEncDeg < -180){
            waveDirnEncDeg = waveDirnEncDeg + 360;
        }

        double waveDirnEnc = waveDirnEncDeg / 180 * Math.PI;
        if(waveDirnEnc >Math.PI){
            waveDirnEnc = waveDirnEnc - 2*Math.PI;
        }else if(waveDirnEnc < -Math.PI){
            waveDirnEnc = waveDirnEnc + 2*Math.PI;
        }

        double xi = waveDirnEnc;

        double a = waveHeight /2;
        double nmdaL = waveNmda / ship.getL();
        double Cxwv = 0.05 -0.2*nmdaL+0.75*Math.pow(nmdaL,2)-0.51*Math.pow(nmdaL,3);
        double Cywv = 0.46 -6.83*nmdaL+15.65*Math.pow(nmdaL,2)+8.44*Math.pow(nmdaL,3);
        double Cnwv = -0.11 -0.68*nmdaL+0.79*Math.pow(nmdaL,2)-0.21*Math.pow(nmdaL,3);

        //放大倍数
        int n = 1;
        Cxwv *= n;
        Cywv *= n;
        Cnwv *= n;

        double Xwv = 0.5 * ship.getRou() * ship.getL() * Math.pow(a,2)*Math.cos(xi)*Cxwv;
        double Ywv = 0.5 * ship.getRou() * ship.getL() * Math.pow(a,2)*Math.sin(xi)*Cywv;
        double Nwv = 0.5 * ship.getRou() * Math.pow(ship.getL(),2) * Math.pow(a,2)*Math.sin(xi)*Cnwv;
        waveForce.setXwv(Xwv);
        waveForce.setYwv(Ywv);
        waveForce.setNwv(Nwv);
        return waveForce;
    }

    //prop_rudder，缺参数
    public PropRudder propRudder(double u,double v,double rRad,double derta){
        PropRudder propRudder = new PropRudder();
        double nP = 50;
        double rudderAngle = derta * Math.PI/180;
        //合速度
        double U = FastMath.hypot(u, v); // 合速度
        double beta = 0.0;
        if (U != 0.0) {
            beta = FastMath.asin(-v / U); // 斜航角
        }

        double v_ = 0.0;
        if (U != 0.0) {
            v_ = v / U; // 无次元化横方向速度
        }
        double r_ = 0.0;
        if (U != 0.0) {
            r_ = rRad * ship.getL() / U; // 无次元化回转角速度
        }
        double J = 0.0;
//        if (nP != 0.0) {
//            J = (1 - ship.getWpo()) * u / (nP * ship.getDp()); // 前进常数 wpo 和 dp都没定义
//        }
//        double K_T = k0 + k1 * J + k2 * Math.pow(J, 2); // 斯特劳斯系数Kx 都没有定义
//        double v_R = U * γR * (beta - lr_ * r_); // 舵流入横方向速度成分
//        double u_R;
//        if (J == 0.0) {
//            u_R = FastMath.sqrt(η * Math.pow(κ * ϵ * 8.0 * k0 * Math.pow(n_p, 2) * Math.pow(Dp, 4) / pi, 2));
//        } else {
//            u_R = u * (1 - wpo) * ϵ * FastMath.sqrt(η * Math.pow(1.0 + κ * (FastMath.sqrt(1.0 + 8.0 * K_T / (pi * Math.pow(J, 2))) - 1), 2) + (1 - η)); // 舵流入前后方向速度成分
//        }
//        double U_R = FastMath.hypot(u_R, v_R); // 舵流入速度
//        double alpha_R = rudderAngle - FastMath.atan2(v_R, u_R); // 舵流入角度
//
//        double F_N = 0.5 * AR * ρ * fa * Math.pow(U_R, 2) * FastMath.sin(alpha_R);
//
//        double X_R = -(1 - tR) * F_N * FastMath.sin(rudderAngle);
//        double X_P = (1 - tP) * ρ * K_T * Math.pow(n_p, 2) * Math.pow(Dp, 4);
//
//        double Y_R = -(1 + aH) * F_N * FastMath.cos(rudderAngle);
//
//        double N_R = (-0.5 + aH * xH) * F_N * FastMath.cos(rudderAngle);
//
//        double Xp = X_R + X_P;
//        double Yp = Y_R;
//        double Np = N_R;

//        return propRudder;
        return  null;
    }

    //shuidonglijisuan_more_params
    //    # 输入船舶对流速度及水动力系数，输出三个方向水动力，采用《A Time-Averaged Method for Ship Maneuvering Prediction in Waves》的式20，更多参数
    public ShuiDongLiJiSuan shuiDongLiJiSuan(double u0,double ur,double vr,double rRad,double uAcc,double vAcc,double rAcc){
        ShuiDongLiJiSuan shuiDongLiJiSuan = new ShuiDongLiJiSuan();
        // # 计算所需参数
        double V = FastMath.sqrt(ur * ur + vr * vr);
        double uUd = 1.0; // 无量纲纵向速度
        double vUd = vr / ur;
        double rUd = rRad * ship.getL() / ur;
        double derta_u_ud = (ur - u0) / ur; // 即derta u'
        double u_De_ud = uAcc * ship.getL() / (ur * ur);
        double v_De_ud = vAcc * ship.getL() / (ur * ur);
        double r_De_ud = rAcc * ship.getL() * ship.getL() / (ur * ur);

        // 计算无量纲水动力系数
        double X_ = X0 + Xu * derta_u_ud + X_uu * Math.pow(derta_u_ud, 2) +
                X_u_De * u_De_ud + X_vv * Math.pow(vUd, 2) + X_rr * Math.pow(rUd, 2) + X_vr * vUd * rUd;

        double Y_ = Y_v * vUd + Y_vvv * Math.pow(vUd, 3) + Y_v_De * v_De_ud +
                Y_r * rUd + Y_rrr * Math.pow(rUd, 3) +
                Y_vvr * Math.pow(vUd, 2) * rUd + Y_vrr * vUd * Math.pow(rUd, 2);

        double N_ = N_v * vUd + N_vvv * Math.pow(vUd, 3) + N_r * rUd + N_rrr * Math.pow(rUd, 3) +
                N_r_De * r_De_ud + N_vvr * Math.pow(vUd, 2) * rUd + N_vrr * vUd * Math.pow(rUd, 2);

        // 还原力的大小
        double XH = X_ * (0.5 * ship.getRou() * ship.getL() * ship .getD() * ur * ur);
        double YH = Y_ * (0.5 * ship.getRou() * ship.getL() * ship .getD() * ur * ur);
        double NH = N_ * (0.5 * ship.getRou() * Math.pow(ship.getL(), 2) * ship .getD() * ur * ur);
        shuiDongLiJiSuan.setNH(NH);
        shuiDongLiJiSuan.setXH(XH);
        shuiDongLiJiSuan.setYH(YH);
        return shuiDongLiJiSuan;
    }

    //# 输入u,v,r为t时刻的速度，dt为步长
    //# 四阶龙格库塔法是为了求出4个加速度，每一阶的输入状态需要更新
    //RongeKuta
    public RongeKuta rongeKuta(double u,double v,double rRad,double uAcc,double vAcc,double rAccRad,double x,double y,
                          double faiDeg,double winDirnInpDeg,double winSpdInp,double curnDirnInpDeg,double curnSpdInp){
        double faiRad = faiDeg * Math.PI / 180.0;

        // 一阶
        double[] k1 = f(u, v, rRad, uAcc, vAcc, rAccRad, faiDeg, winDirnInpDeg, winSpdInp, curnDirnInpDeg, curnSpdInp);
        double k1_u_ = k1[0], k1_v_ = k1[1], k1_r_ = k1[2];
        double k1_u = u + dt / 2 * k1_u_;
        double k1_v = v + dt / 2 * k1_v_;
        double k1_r = rRad + dt / 2 * k1_r_;
        double[] k1_xyz = calPos(k1_u, k1_v, k1_r, faiDeg);
        double k1_fai_rad_ = k1_xyz[2];
        double k1_fai_rad = faiRad + dt / 2 * k1_fai_rad_;
        double k1_fai_deg = k1_fai_rad * 180 / Math.PI;

        // 二阶
        double[] k2 = f(k1_u, k1_v, k1_r, uAcc, vAcc, rAccRad, k1_fai_deg, winDirnInpDeg, winSpdInp, curnDirnInpDeg, curnSpdInp);
        double k2_u_ = k2[0], k2_v_ = k2[1], k2_r_ = k2[2];
        double k2_u = u + dt / 2 * k2_u_;
        double k2_v = v + dt / 2 * k2_v_;
        double k2_r = rRad + dt / 2 * k2_r_;
        double[] k2_xyz = calPos(k1_u, k1_v, k1_r, k1_fai_deg);
        double k2_fai_rad_ = k2_xyz[2];
        double k2_fai_rad = faiRad + dt / 2 * k2_fai_rad_;
        double k2_fai_deg = k2_fai_rad * 180 / Math.PI;

        // 三阶
        double[] k3 = f(k2_u, k2_v, k2_r, uAcc, vAcc, rAccRad, k2_fai_deg, winDirnInpDeg, winSpdInp, curnDirnInpDeg, curnSpdInp);
        double k3_u_ = k3[0], k3_v_ = k3[1], k3_r_ = k3[2];
        double k3_u = u + dt * k3_u_;
        double k3_v = v + dt * k3_v_;
        double k3_r = rRad + dt * k3_r_;
        double[] k3_xyz = calPos(k2_u, k2_v, k2_r, k2_fai_deg);
        double k3_fai_rad_ = k3_xyz[2];
        double k3_fai_rad = faiRad + dt * k3_fai_rad_;
        double k3_fai_deg = k3_fai_rad * 180 / Math.PI;

        // 四阶
        double[] k4 = f(k3_u, k3_v, k3_r, uAcc, vAcc, rAccRad, k3_fai_deg, winDirnInpDeg, winSpdInp, curnDirnInpDeg, curnSpdInp);
        double k4_x_ = k4[0], k4_y_ = k4[1], k4_fai_rad_ = k4[2];

        // 真实解
        double u_next = u + dt * (k1[0] + 2 * k2[0] + 2 * k3[0] + k4[0]) / 6.0;
        double v_next = v + dt * (k1[1] + 2 * k2[1] + 2 * k3[1] + k4[1]) / 6.0;
        double r_next = rRad + dt * (k1[2] + 2 * k2[2] + 2 * k3[2] + k4[2]) / 6.0;

        double x_next = x + dt * (k1_xyz[0] + 2 * k2_xyz[0] + 2 * k3_xyz[0] + k4_x_) / 6.0;
        double y_next = y + dt * (k1_xyz[1] + 2 * k2_xyz[1] + 2 * k3_xyz[1] + k4_y_) / 6.0;

        double fai_rad_next = faiRad + dt * (k1_fai_rad_ + 2 * k2_fai_rad_ + 2 * k3_fai_rad_ + k4_fai_rad_) / 6.0;
        double fai_deg_next = fai_rad_next * 180 / Math.PI;

        double heading_deg_next = (fai_rad_next - FastMath.atan2(v_next, u_next)) * 180 / Math.PI;
        double piaojiao_rad = FastMath.atan2(v_next, u_next);

        if (fai_deg_next < 0) {
            fai_deg_next += 360;
        } else if (fai_deg_next >= 360) {
            fai_deg_next -= 360;
        }

        // 加速度
        uAcc = (k1[0] + 2 * k2[0] + 2 * k3[0] + k4[0]) / 6.0;
        vAcc = (k1[1] + 2 * k2[1] + 2 * k3[1] + k4[1]) / 6.0;
        rAccRad = (k1[2] + 2 * k2[2] + 2 * k3[2] + k4[2]) / 6.0;

        // 返回结果
        RongeKuta rongeKuta = new RongeKuta();
        rongeKuta.setUNext(u_next);
        rongeKuta.setVNext(v_next);
        rongeKuta.setRNext(r_next);
        rongeKuta.setXNext(x_next);
        rongeKuta.setYNext(y_next);
        rongeKuta.setFaiDegNext(fai_deg_next);
        rongeKuta.setHeadingDegNext(heading_deg_next);
        rongeKuta.setPiaoJiaoRad(piaojiao_rad);
        rongeKuta.setUAcc(uAcc);
        rongeKuta.setVAcc(vAcc);
        rongeKuta.setRAccRad(rAccRad);
        return rongeKuta;
    }

    //f，这位更是重量级，命名f还jb到处调用
    //    # 在某状态u,v,r，流速流向、风速风向，油门，桨角下，求解数值导数u_,v_,r_, 也给出遭遇风向，遭遇风速，给出对水速度ur,vr
    //    # XH,YH,NH需要使用ur,vr计算，因是对水的水动力
    //    # Xwd,Ywd,Nwd需要使用u,v计算，因为风是对地
    //    # Xp,Yp,Np需要使用ur,vr计算
    public double[] f(double u,double v,double rRad,double uAcc,double vAcc,double rAccRad,
    double faiDeg,double winDirnInpDeg,double winSpdInp,double curnDirnInpDeg,double curnSpdInp){
        double Xwd, Ywd, Nwd, winDirnEncDeg, winSpdEnc, winThetaEnc;
        //# 风力与流体速度和方向无关
        if(windSwitch){
            WindForce windForce = windForce(u, v, faiDeg, winDirnInpDeg, winSpdInp);
            Xwd = windForce.getXwd();
            Ywd = windForce.getYwd();
            Nwd = windForce.getNwd();
            winDirnEncDeg = windForce.getWinDirnEncDeg();
            winSpdEnc = windForce.getWinSpdEnc();
            winThetaEnc = windForce.getWinThetaEnc();
        }else {
            Xwd = 0.0;
            Ywd = 0.0;
            Nwd = 0.0;
            winDirnEncDeg = 0.0;
            winSpdEnc = 0.0;
            winThetaEnc = 0.0;
        }

        double Xwv, Ywv, Nwv;
        //# 暂时先考虑浪与时间无关
        if (waveSwitch){
            WaveForce waveForce = waveForce(faiDeg, waveNmda, waveDirnInpDeg, waveHeight);
            Xwv=waveForce.getXwv();
            Ywv=waveForce.getYwv();
            Nwv=waveForce.getNwv();
        }else{
            Xwv=0.0;
            Ywv=0.0;
            Nwv=0.0;
        }

        double ur, vr, uc, vc;
        //# 船舶运动相对水流的速度
        if (curnSwitch) {
            CurrentRelative currentRelative = currentRelative(u, v, faiDeg, curnDirnInpDeg, curnSpdInp);
            ur = currentRelative.getUr();
            vr = currentRelative.getVr();
            uc = currentRelative.getUc();
            vc = currentRelative.getVc();
        }else {
            ur=u;
            vr=v;
            uc=0.0;
            vc = 0.0;
        }

        double XH, YH, NH;
        //这里缺u0，暂时用0
        ShuiDongLiJiSuan shuiDongLiJiSuan = shuiDongLiJiSuan(0.0,ur, vr, rRad, uAcc, vAcc, rAccRad);
        //# 水动力计算用绝对速度，不要用对水速度就可以
        XH = shuiDongLiJiSuan.getXH();
        YH = shuiDongLiJiSuan.getYH();
        NH = shuiDongLiJiSuan.getNH();

        //# Xp, Yp, Np = self.diaocang_prop_mthod2(ur, vr, r_rad, self.youmen, self.Derta_deg)

        double XP, YP, NP, XR, YR, NR;
        //这个函数缺参数，暂时用0
        //DoublePropRudderChangde doublePropRudderChangde = doublePropRudderChange(ur, vr, rRad, ship.getN(), ship.getDertaDeg())
        XP = 0.0;
        YP = 0.0;
        NP = 0.0;
        XR = 0.0;
        YR = 0.0;
        NR = 0.0;
        //# Xp, Yp, Np = self.prop_rudder(u, v, r_rad, self.Derta)

        double u_ = ( XP + XR + XH + Xwd + Xwv + (ship.getM() + ship.getMY()) * (vr * rRad + ship.getXG() * Math.pow(rRad,2)) + (ship.getM() + ship.getMX()) * rRad * vc)/(ship.getM() + ship.getMX());
        double v_ = ( YP + YR + YH + Ywd + Ywv - (ship.getM() + ship.getMX()) * ur * rRad - (ship.getM() + ship.getMY()) * rRad * uc)/(ship.getM() + ship.getMX());
        double r_ = ( NP + NR + NH + Nwd + Nwv - ship.getM() * ship.getXG() * ur * rRad) / ( ship.getIzz() + ship.getJzz() );

        double xLeftShort = XP + XR + XH + Xwd + Xwv;
        double yLeftShort = YP + YR + YH + Ywd + Ywv;
        double nLeftShort = NP + NR + NH + Nwd + Nwv;

        double xLeftLong = XP + XR + XH + Xwd + Xwv + (ship.getM() + ship.getMY()) * (vr * rRad + ship.getXG() * Math.pow(rRad,2)) + (ship.getM() + ship.getMX()) * rRad * vc;
        double yLeftLong = YP + YR + YH + Ywd + Ywv - (ship.getM() + ship.getMX()) * ur * rRad - (ship.getM() + ship.getMY()) * rRad * uc;
        double nLeftLong = NP + NR + NH + Nwd + Nwv - ship.getM() * ship.getXG() * ur * rRad;

        return new double[]{u_,v_,r_,XH,YH,NH,XP,YP,NP,XR,YR,NR,Xwd,Ywd,Nwd,Xwv,Ywv,Nwv,ur,vr,
                winDirnEncDeg,winSpdEnc,winThetaEnc,xLeftShort,yLeftShort,nLeftShort,xLeftLong,yLeftLong,nLeftLong};
    }

    //cal_pos
    // 在某状态u,v,r下，求解位置量导数x_,y_fai_
    public double[] calPos(double u,double v,double r,double faiDeg){
        double faiRad = faiDeg * Math.PI/180;
        double x_ = u*Math.cos(faiRad) - v*Math.sin(faiRad);
        double y_ = u*Math.sin(faiRad) + v*Math.cos(faiRad);
        double faiRad_ = r;

        return new double[]{x_,y_,faiRad_};
    }

    //getXYNHformuvr
    //根据状态值，计算XH，YH，NH，只是用于SVR辨识前的检验，不是运动预报; 考虑无风的情况, 且假设流速为0
    public GetXYNHformuvr getXYNHformuvr(double u,double v,double rRad,double u_,double v_,double r_,
                                         double XP,double YP,double NP,double XR,double YR,double NR){
        double uc = 0.0;
        double vc = 0.0;

        double XH =u_ * (ship.getM() + ship.getMX()) - XP - XR - (ship.getM() + ship.getMY()) * (v * rRad + ship.getXG() * Math.pow(rRad, 2)) - (ship.getM() + ship.getMX()) * rRad * vc;
        double YH =v_ * (ship.getM() + ship.getMY()) - YP - YR + (ship.getM() + ship.getMX()) * u * rRad + (ship.getM() + ship.getMY()) * rRad * uc;
        double NH =r_ * (ship.getIzz() + ship.getJzz()) - NP - NR + ship.getM() * ship.getXG() * u * rRad;

        GetXYNHformuvr getXYNHformuvr = new GetXYNHformuvr();
        getXYNHformuvr.setNH(NH);
        getXYNHformuvr.setXH(XH);
        getXYNHformuvr.setYH(YH);
        return getXYNHformuvr;
    }

    //calcuCxwCywCnw
    //根据公式直接把Cxw，Cyw, Cnw的几个值弄出来，输出13个数据
    public CalcuCxwCywCnw calcuCxwCywCnw(double u,double v,double rRad,double V){
        double[] Cxw = {u * u, v * v, Math.pow(ship.getL(), 2) * Math.pow(rRad, 2)};
        double[] Cyw = {V * v, ship.getL() * V * rRad, Math.abs(v) * v, ship.getL() * Math.abs(v) * rRad, Math.pow(ship.getL(), 2) * Math.abs(rRad) * rRad};
        double[] Cnw = {V * v, ship.getL() * V * rRad, Math.pow(ship.getL(), 2) * Math.abs(rRad) * rRad, ship.getL() * Math.pow(v, 2) * rRad / V, Math.pow(ship.getL(), 2) * v * Math.pow(rRad, 2) / V};

        CalcuCxwCywCnw calcuCxwCywCnw = new CalcuCxwCywCnw();
        calcuCxwCywCnw.setCnw(Cnw);
        calcuCxwCywCnw.setCxw(Cxw);
        calcuCxwCywCnw.setCyw(Cyw);
        return calcuCxwCywCnw;
    }

    //calcuCxwCywCnw_with_moreparams
    //按照闫建喜的公式做的新的，直接用u作为特征速度
    public CalcuCxwCywCnw calcuCxwCywCnwWithMoreParams(double u0,double u,double v,double rRad,double uAcc,double vAcc,double rAccRad){
        double dertaU = u -u0;
        double[] Cxw = {u * u, u * dertaU, dertaU * dertaU, ship.getL() * uAcc, v * v, Math.pow(ship.getL(), 2) * Math.pow(rRad, 2), ship.getL() * v * rRad};
        double[] Cyw = {u * v, Math.pow(v, 3) / u, ship.getL() * vAcc, ship.getL() * u * rRad, Math.pow(ship.getL(), 3) * Math.pow(rRad, 3) / u, ship.getL() * Math.pow(v, 2) * rRad / u, Math.pow(ship.getL(), 2) * v * Math.pow(rRad, 2) / u};
        double[] Cnw = {u * v, Math.pow(v, 3) / u, ship.getL() * u * rRad, Math.pow(ship.getL(), 3) * Math.pow(rRad, 3) / u, ship.getL() * rAccRad, ship.getL() * Math.pow(v, 2) * rRad / u, Math.pow(ship.getL(), 2) * v * Math.pow(rRad, 2) / u};

        CalcuCxwCywCnw calcuCxwCywCnw = new CalcuCxwCywCnw();
        calcuCxwCywCnw.setCnw(Cnw);
        calcuCxwCywCnw.setCxw(Cxw);
        calcuCxwCywCnw.setCyw(Cyw);
        return calcuCxwCywCnw;
    }

    //oneround
    //跑一轮，在设定油门，舵角的情况下，跑一次直航50s平稳后回转


    //oneZ

    //TestXYNJ

}
