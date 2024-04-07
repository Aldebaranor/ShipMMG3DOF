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
    private Double w;
    //船舶质量
    private Double m;
    //船舶排水体积系数
    private Double Cb;
    //
//    private Double u0;
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




    //计算船体参数get方法
    public void setW() {
        this.w = 10 * rou;
    }

    public void setM() {
        this.m = w;
    }

    public void setCb() {
        this.Cb = w/(l*b*d)/rou;
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
