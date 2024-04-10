package song.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "ship")
public class ShipConfig {

    //船体属性
        private Double suochibi;
        private Double change;
        private Double panmianbi;
        private Double luojubi;
        private Double miuR;
        private Double nmdaRud;

        private Double L;
        private Double B;
        private Double D;
        private Double Rou;
        private Double RouAir;
        private Double G;
        private Double U0;
        private Double MX;
        private Double MY;
        private Double Izz;
        //桨属性
        private Double TGanxian;
        private Double TSuperStru;
        private Double T;
        private Double Youmen;
    //实际进入计算的舵角
        private Double DertaDegInput;
    //设定的舵角
        private Double DertaDeg;
        private Boolean WindSwitch;
        private Double WinDirnInpDeg;
    //单位m/s
        private Double WinSpdInp;
        private Boolean CurnSwitch;
    //180度减速，0度加速，90度北移，-90度南移
        private Double CurnDirnInpDeg;
    //流速不要超过0.5
        private Double CurnSpdInp;
        private Boolean WaveSwitch;
    //波高波向，与浪向相同
        private Double WaveDirnInpDeg;
    //波高
        private Double WaveHeight;
    //是否开启调试绘制
        private Boolean DrawingSwitch;
    //打舵是根据轨迹自动控制，否：舵角由人指定
        private Boolean ControlSwitch;
    //是否真实TCP通信
        private Boolean IsRealTCPUDP;
    //是否开始仿真
        private Boolean IsStartSimulation;

    //状态量
    //实时位置
        private Double X;
        private Double Y;
        private Double YDraw;
        private Double FaiDeg;
        private Double HeadingDeg;
    //速度
        private Double U;
        private Double V;
        private Double RRad;
        private Double UAcc;
        private Double VAcc;
        private Double RAccRad;
    //让船稳定跑成直航的时间
        private Double TStable;
        private Double TMax;

        private Boolean JustStart;
    //默认为停止状态
        private Double PingTaiXunHangMoShi;
    //判断船舶进入范围的距离
        private Double Range;
    //判断是否到终点,到了会进入StopProcess
        private Boolean Finished;
    //判断是否是StopProcess中的第一个周期，第一个周期时需要求均值
        private Boolean JustToStop;
        private Boolean StopSuccess;

}
