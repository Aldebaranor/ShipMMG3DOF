package song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import song.model.Contents;
import song.model.RongeKuta;
import song.model.Ship;
import song.utils.GeoUtils;

import java.util.List;

@Service
public class Simulation {

    @Autowired
    private motionService motionService;

    //跑一轮，在设定油门，舵角的情况下，跑一次直航50s平稳后回转
    public void oneround(double n,double derta,double pathFinal){
        Ship ship = motionService.init();
//        ship.setN() = n;
        ship.setDertaDegInput(derta);

        while (ship.getT()<ship.getTMax()){
            //开始时直航，一段时间后速度平稳，再进行转向
            if(ship.getT()>ship.getTStable()){
                ship.setDertaDeg(GeoUtils.dertaShouldbe(ship.getDertaDegInput(),ship.getDertaDeg(),ship.getChange(), Contents.dt));
            }

            //按当前状态和风浪流环境情况，计算XH，Xp, Xwd等力，只用于保存和输出，并不参与运动预报
            double [] forceArray = motionService.f(ship.getU(),ship.getV(),ship.getRRad(),ship.getUAcc(),ship.getVAcc(),ship.getRAccRad(),
                    ship.getFaiDeg(),ship.getWinDirnInpDeg(),ship.getWinSpdInp(),ship.getCurnDirnInpDeg(),ship.getCurnSpdInp());
            double XH, YH, NH, Xp, Yp, Np, XR, YR, NR, Xwd, Ywd, Nwd, ur, vr;
            XH = forceArray[3];
            YH = forceArray[4];
            NH = forceArray[5];
            Xp = forceArray[6];
            Yp = forceArray[8];
            Np = forceArray[9];
            XR = forceArray[10];
            YR = forceArray[11];
            NR = forceArray[12];
            Xwd = forceArray[13];
            Ywd = forceArray[14];
            Nwd = forceArray[15];
            ur = forceArray[19];
            vr = forceArray[20];

            //龙格库塔计算迭代值，运动预报
            RongeKuta rongeKuta = motionService.rongeKuta(ship.getU(),ship.getV(),ship.getRRad(),ship.getUAcc(),ship.getVAcc(),ship.getRAccRad(),
                    ship.getX(),ship.getY(),ship.getFaiDeg(),ship.getWinDirnInpDeg(),ship.getWinSpdInp(),ship.getCurnDirnInpDeg(),ship.getCurnSpdInp());

            double headingDegNext, piaojiaoRad, uAcc, vAcc, rAccRad;
            ship.setU(rongeKuta.getUNext());
            ship.setV(rongeKuta.getVNext());
            ship.setRRad(rongeKuta.getRNext());
            ship.setX(rongeKuta.getXNext());
            ship.setY(rongeKuta.getYNext());
            ship.setFaiDeg(rongeKuta.getFaiDegNext());
            headingDegNext = rongeKuta.getHeadingDegNext();
            piaojiaoRad = rongeKuta.getPiaoJiaoRad();
            uAcc = rongeKuta.getUAcc();
            vAcc = rongeKuta.getVAcc();
            rAccRad = rongeKuta.getRAccRad();
            double V = Math.sqrt(Math.pow(ship.getU(),2) + Math.pow(ship.getV(),2));
            ship.setT(ship.getT()+Contents.dt);

            //以下记录绘图值，y轴要进行反转，从指向上转为指向下
            //全是数组append，暂不处理

        }

        boolean flagSwitchDraw = false;
        if(flagSwitchDraw){
            //TODO:绘图功能，可以采用java Swing或者JavaFX Charts、JFreeChart
        }else {
            //TODO:绘图功能
        }

        //TODO：绘图文件保存

    }

    //跑Z形操舵试验，在设定油门，舵角的情况下，跑一次直航50s平稳后开始进行Z形操舵试验
    //Derta为Z形操舵的目标舵角，为正数
    public void oneZ(double n,double dertaZ,double pathFinal){
        Ship ship = motionService.init();
//        ship.setN() = n;
        //舵角切换次数标识符，case_n在1,3,5时要打右舵，2,4时打左舵，6时停止
        int caseN = 0;
        while ((caseN<6&&dertaZ!=0)||(dertaZ==0&&ship.getT()<ship.getTMax())){
            double faiDegLast,faiDegNow;
            //TODO：原python程序此处有逻辑错误，这两个变量不走第一个if是没有初始值的，这里暂时给个0，不知道具体给多少
            faiDegLast = 0;
            faiDegNow = 0;
            if(ship.getT()>ship.getTStable()){
                List<Double> fddArray = ship.getFaiDegDrawArray();
                faiDegLast = fddArray.get(fddArray.size()-2);
                faiDegNow = fddArray.get(fddArray.size()-1);
            }
            if(ship.getT()<=ship.getTStable()){
                caseN = 0;
            }else if(caseN == 0){//开始时直航，50s后开始Z形操舵
                caseN++;
            }else if(((faiDegLast<=dertaZ)&&(faiDegNow>dertaZ))||((faiDegLast<dertaZ)&&(faiDegNow>=dertaZ))){//首向角跨过右舵角
                caseN++;
            }else if(((faiDegLast>=-dertaZ)&&(faiDegNow<-dertaZ))||((faiDegLast>-dertaZ)&&(faiDegNow<=-dertaZ))){//首向角跨过左舵角
                caseN++;
            }
            if(caseN==0){
                ship.setDertaDegInput(0.0);//直航阶段
            }
            if(caseN==1 || caseN == 3 || caseN ==5){//打左舵
                 ship.setDertaDegInput(dertaZ);//打右舵
            }
            if(caseN==2 || caseN ==4){//打右舵
                ship.setDertaDegInput(-dertaZ);//打右舵
            }

            ship.setDertaDeg(GeoUtils.dertaShouldbe(ship.getDertaDegInput(),ship.getDertaDeg(),ship.getChange(),Contents.dt));

            //按当前状态和风浪流环境情况，计算XH，Xp, Xwd等力，只用于保存和输出，并不参与运动预报
            double [] forceArray = motionService.f(ship.getU(),ship.getV(),ship.getRRad(),ship.getUAcc(),ship.getVAcc(),ship.getRAccRad(),
                    ship.getFaiDeg(),ship.getWinDirnInpDeg(),ship.getWinSpdInp(),ship.getCurnDirnInpDeg(),ship.getCurnSpdInp());
            double XH, YH, NH, Xp, Yp, Np, XR, YR, NR, Xwd, Ywd, Nwd, ur, vr;
            XH = forceArray[3];
            YH = forceArray[4];
            NH = forceArray[5];
            Xp = forceArray[6];
            Yp = forceArray[8];
            Np = forceArray[9];
            XR = forceArray[10];
            YR = forceArray[11];
            NR = forceArray[12];
            Xwd = forceArray[13];
            Ywd = forceArray[14];
            Nwd = forceArray[15];
            ur = forceArray[19];
            vr = forceArray[20];

            //龙格库塔计算迭代值，运动预报
            RongeKuta rongeKuta = motionService.rongeKuta(ship.getU(),ship.getV(),ship.getRRad(),ship.getUAcc(),ship.getVAcc(),ship.getRAccRad(),
                    ship.getX(),ship.getY(),ship.getFaiDeg(),ship.getWinDirnInpDeg(),ship.getWinSpdInp(),ship.getCurnDirnInpDeg(),ship.getCurnSpdInp());

            double headingDegNext, piaojiaoRad, uAcc, vAcc, rAccRad;
            ship.setU(rongeKuta.getUNext());
            ship.setV(rongeKuta.getVNext());
            ship.setRRad(rongeKuta.getRNext());
            ship.setX(rongeKuta.getXNext());
            ship.setY(rongeKuta.getYNext());
            ship.setFaiDeg(rongeKuta.getFaiDegNext());
            headingDegNext = rongeKuta.getHeadingDegNext();
            piaojiaoRad = rongeKuta.getPiaoJiaoRad();
            uAcc = rongeKuta.getUAcc();
            vAcc = rongeKuta.getVAcc();
            rAccRad = rongeKuta.getRAccRad();
            double V = Math.sqrt(Math.pow(ship.getU(),2) + Math.pow(ship.getV(),2));
            ship.setT(ship.getT()+Contents.dt);

            //以下记录绘图值，y轴要进行反转，从指向上转为指向下
            //全是数组append，暂不处理

        }

        boolean flagSwitchDraw = false;
        if(flagSwitchDraw){
            //TODO:绘图功能，可以采用java Swing或者JavaFX Charts、JFreeChart
        }else {
            //TODO:绘图功能
        }

        //TODO：绘图文件保存


    }

}
