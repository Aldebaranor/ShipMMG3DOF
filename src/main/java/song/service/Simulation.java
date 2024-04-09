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
    public void oneround(double n,double derta,String pathFinal){
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
            //全是数组append，绘图从这些数组里面取数据
            List<Double> uArray = ship.getUArray();
            uArray.add(ship.getU());
            ship.setUArray(uArray);

            List<Double> vArray = ship.getVArray();
            vArray.add(ship.getV());
            ship.setVArray(vArray);

            List<Double> urArray = ship.getUrArray();
            urArray.add(ur);
            ship.setUrArray(urArray);

            List<Double> vrArray = ship.getVrArray();
            vrArray.add(vr);
            ship.setVArray(vrArray);

            //他命名好多问题，又用V又用v
            List<Double> VArray = ship.getV_Array();
            VArray.add(V);
            ship.setV_Array(VArray);

            double rDeg = ship.getRRad()*180/Math.PI;

            List<Double> rDegArray = ship.getRDegArray();
            rDegArray.add(rDeg);
            ship.setRDegArray(rDegArray);

            List<Double> rRadArray = ship.getRRadArray();
            rRadArray.add(ship.getRRad());
            ship.setRRadArray(rRadArray);

            List<Double> xArray = ship.getXArray();
            xArray.add(ship.getX());
            ship.setXArray(xArray);

            //y正常的进行计算，只是在绘图或输出时进行翻转，逆时针变顺时针
            ship.setYDraw(-ship.getY());
            List<Double> yDrawArray = ship.getYDrawArray();
            yDrawArray.add(ship.getYDraw());
            ship.setYDrawArray(yDrawArray);

            List<Double> faiDegArray = ship.getFaiDegArray();
            faiDegArray.add(ship.getFaiDeg());
            ship.setFaiDegArray(faiDegArray);

            double faiDegDraw;
            if(ship.getFaiDeg() <180){
                faiDegDraw = ship.getFaiDeg();
            }else {
                faiDegDraw = ship.getFaiDeg()-360;
            }
            List<Double> faiDegDrawArray = ship.getFaiDegDrawArray();
            faiDegDrawArray.add(faiDegDraw);
            ship.setFaiDegDrawArray(faiDegDrawArray);

            List<Double> headingArray = ship.getHeadingArray();
            headingArray.add(headingDegNext);
            ship.setHeadingArray(headingArray);

            List<Double> piaoJiaoArray = ship.getPiaoJiaoArray();
            piaoJiaoArray.add(piaojiaoRad);
            ship.setPiaoJiaoArray(piaoJiaoArray);

            List<Double> piaojiaoArrayDeg = ship.getPiaoJiaoArrayDeg();
            piaojiaoArrayDeg.add(piaojiaoRad * 180/Math.PI);
            ship.setPiaoJiaoArrayDeg(piaojiaoArrayDeg);

            List<Double> uAccArray = ship.getUAccArray();
            uAccArray.add(uAcc);
            ship.setUAccArray(uAccArray);

            List<Double> vAccArray = ship.getVAccArray();
            vAccArray.add(vAcc);
            ship.setVAccArray(vAccArray);

            List<Double> rAccRadArray = ship.getRAccRadArray();
            rAccRadArray.add(rAccRad);
            ship.setRAccRadArray(rAccRadArray);

            //
            List<Double> XpArray = ship.getXpArray();
            XpArray.add(Xp);
            ship.setXpArray(XpArray);
            List<Double> YpArray = ship.getYpArray();
            YpArray.add(Yp);
            ship.setYpArray(YpArray);
            List<Double> NpArray = ship.getNpArray();
            NpArray.add(Np);
            ship.setNpArray(NpArray);
            List<Double> XRArray = ship.getXRArray();
            XRArray.add(XR);
            ship.setXRArray(XRArray);
            List<Double> YRArray = ship.getYRArray();
            YRArray.add(YR);
            ship.setYRArray(YRArray);
            List<Double> NRArray = ship.getNRArray();
            NRArray.add(NR);
            ship.setNRArray(NRArray);
            List<Double> XHArray = ship.getXHArray();
            XHArray.add(XH);
            ship.setXHArray(XHArray);
            List<Double> YHArray = ship.getYHArray();
            YHArray.add(YH);
            ship.setYHArray(YHArray);
            List<Double> NHArray = ship.getNHArray();
            NHArray.add(NH);
            ship.setNHArray(NHArray);
            List<Double> XwdArray = ship.getXwdArray();
            XwdArray.add(Xwd);
            ship.setXwdArray(XwdArray);
            List<Double> YwdArray = ship.getYwdArray();
            YwdArray.add(Ywd);
            ship.setYwdArray(YwdArray);
            List<Double> NwdArray = ship.getNwdArray();
            NwdArray.add(Nwd);
            ship.setNwdArray(NwdArray);
//            List<Double> XwvArray = ship.getXwvArray();
//            XwvArray.add(Xwv);
//            ship.setXwvArray(XwvArray);
//            List<Double> YwvArray = ship.getYwvArray();
//            YwvArray.add(Ywv);
//            ship.setYwvArray(YwvArray);
//            List<Double> NwvArray = ship.getNwvArray();
//            NwvArray.add(Nwv);
//            ship.setNwvArray(NwvArray);

            List<Double> tArray = ship.getTArray();
            tArray.add(ship.getT());
            ship.setTArray(tArray);
            //todo：模型的n
//            List<Double> nArray = ship.getNArray();
//            nArray.add(ship.getN());
//            ship.setYwdArray(nArray);
            List<Double> dertaDegArray = ship.getDertaDegArray();
            dertaDegArray.add(ship.getDertaDeg());
            ship.setDertaDegArray(dertaDegArray);

        }
//        要打印的数据可以在这里打印
//        System.out.println("");
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
    public void oneZ(double n,double dertaZ,String pathFinal){
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
            double XH, YH, NH, Xp, Yp, Np, XR, YR, NR, Xwd, Ywd, Nwd, Xwv, Ywv, Nwv, ur, vr;
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
            Xwv = forceArray[16];
            Ywv = forceArray[17];
            Nwv = forceArray[18];
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
            //以下记录绘图值，y轴要进行反转，从指向上转为指向下
            //全是数组append，绘图从这些数组里面取数据
            List<Double> uArray = ship.getUArray();
            uArray.add(ship.getU());
            ship.setUArray(uArray);

            List<Double> vArray = ship.getVArray();
            vArray.add(ship.getV());
            ship.setVArray(vArray);

            List<Double> urArray = ship.getUrArray();
            urArray.add(ur);
            ship.setUrArray(urArray);

            List<Double> vrArray = ship.getVrArray();
            vrArray.add(vr);
            ship.setVArray(vrArray);

            //他命名好多问题，又用V又用v
            List<Double> VArray = ship.getV_Array();
            VArray.add(V);
            ship.setV_Array(VArray);

            double rDeg = ship.getRRad()*180/Math.PI;

            List<Double> rDegArray = ship.getRDegArray();
            rDegArray.add(rDeg);
            ship.setRDegArray(rDegArray);

            List<Double> rRadArray = ship.getRRadArray();
            rRadArray.add(ship.getRRad());
            ship.setRRadArray(rRadArray);

            List<Double> xArray = ship.getXArray();
            xArray.add(ship.getX());
            ship.setXArray(xArray);

            //y正常的进行计算，只是在绘图或输出时进行翻转，逆时针变顺时针
            ship.setYDraw(-ship.getY());
            List<Double> yDrawArray = ship.getYDrawArray();
            yDrawArray.add(ship.getYDraw());
            ship.setYDrawArray(yDrawArray);

            List<Double> faiDegArray = ship.getFaiDegArray();
            faiDegArray.add(ship.getFaiDeg());
            ship.setFaiDegArray(faiDegArray);

            double faiDegDraw;
            if(ship.getFaiDeg() <180){
                faiDegDraw = ship.getFaiDeg();
            }else {
                faiDegDraw = ship.getFaiDeg()-360;
            }
            List<Double> faiDegDrawArray = ship.getFaiDegDrawArray();
            faiDegDrawArray.add(faiDegDraw);
            ship.setFaiDegDrawArray(faiDegDrawArray);

            List<Double> headingArray = ship.getHeadingArray();
            headingArray.add(headingDegNext);
            ship.setHeadingArray(headingArray);

            List<Double> piaoJiaoArray = ship.getPiaoJiaoArray();
            piaoJiaoArray.add(piaojiaoRad);
            ship.setPiaoJiaoArray(piaoJiaoArray);

            List<Double> piaojiaoArrayDeg = ship.getPiaoJiaoArrayDeg();
            piaojiaoArrayDeg.add(piaojiaoRad * 180/Math.PI);
            ship.setPiaoJiaoArrayDeg(piaojiaoArrayDeg);

            List<Double> uAccArray = ship.getUAccArray();
            uAccArray.add(uAcc);
            ship.setUAccArray(uAccArray);

            List<Double> vAccArray = ship.getVAccArray();
            vAccArray.add(vAcc);
            ship.setVAccArray(vAccArray);

            List<Double> rAccRadArray = ship.getRAccRadArray();
            rAccRadArray.add(rAccRad);
            ship.setRAccRadArray(rAccRadArray);

            //
            List<Double> XpArray = ship.getXpArray();
            XpArray.add(Xp);
            ship.setXpArray(XpArray);
            List<Double> YpArray = ship.getYpArray();
            YpArray.add(Yp);
            ship.setYpArray(YpArray);
            List<Double> NpArray = ship.getNpArray();
            NpArray.add(Np);
            ship.setNpArray(NpArray);
            List<Double> XRArray = ship.getXRArray();
            XRArray.add(XR);
            ship.setXRArray(XRArray);
            List<Double> YRArray = ship.getYRArray();
            YRArray.add(YR);
            ship.setYRArray(YRArray);
            List<Double> NRArray = ship.getNRArray();
            NRArray.add(NR);
            ship.setNRArray(NRArray);
            List<Double> XHArray = ship.getXHArray();
            XHArray.add(XH);
            ship.setXHArray(XHArray);
            List<Double> YHArray = ship.getYHArray();
            YHArray.add(YH);
            ship.setYHArray(YHArray);
            List<Double> NHArray = ship.getNHArray();
            NHArray.add(NH);
            ship.setNHArray(NHArray);
            List<Double> XwdArray = ship.getXwdArray();
            XwdArray.add(Xwd);
            ship.setXwdArray(XwdArray);
            List<Double> YwdArray = ship.getYwdArray();
            YwdArray.add(Ywd);
            ship.setYwdArray(YwdArray);
            List<Double> NwdArray = ship.getNwdArray();
            NwdArray.add(Nwd);
            ship.setNwdArray(NwdArray);
            List<Double> XwvArray = ship.getXwvArray();
            XwvArray.add(Xwv);
            ship.setXwvArray(XwvArray);
            List<Double> YwvArray = ship.getYwvArray();
            YwvArray.add(Ywv);
            ship.setYwvArray(YwvArray);
            List<Double> NwvArray = ship.getNwvArray();
            NwvArray.add(Nwv);
            ship.setNwvArray(NwvArray);

            List<Double> tArray = ship.getTArray();
            tArray.add(ship.getT());
            ship.setTArray(tArray);
            //todo:模型的N
//            List<Double> nArray = ship.getNArray();
//            nArray.add(ship.getN());
//            ship.setYwdArray(nArray);
            List<Double> dertaDegArray = ship.getDertaDegArray();
            dertaDegArray.add(ship.getDertaDeg());
            ship.setDertaDegArray(dertaDegArray);

        }
//        要打印的数据可以在这里打印
//        System.out.println("");

        boolean flagSwitchDraw = false;
        if(flagSwitchDraw){
            //TODO:绘图功能，可以采用java Swing或者JavaFX Charts、JFreeChart
        }else {
            //TODO:绘图功能
        }

        //TODO：绘图文件保存


    }

}
