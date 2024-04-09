package song.service;

import song.model.*;

import java.util.List;

public interface motionService {

    Ship init();

    CalcuCxwCywCnw calcuCxwCywCnwWithMoreParams(double u0, double u, double v, double rRad, double uAcc, double vAcc, double rAccRad);

    CalcuCxwCywCnw calcuCxwCywCnw(double u,double v,double rRad,double V);

    GetXYNHformuvr getXYNHformuvr(double u, double v, double rRad, double u_, double v_, double r_, double XP, double YP, double NP, double XR, double YR, double NR);

    double[] calPos(double u,double v,double r,double faiDeg);

    double[] f(double u,double v,double rRad,double uAcc,double vAcc,double rAccRad, double faiDeg,double winDirnInpDeg,double winSpdInp,double curnDirnInpDeg,double curnSpdInp);

    RongeKuta rongeKuta(double u, double v, double rRad, double uAcc, double vAcc, double rAccRad, double x, double y, double faiDeg, double winDirnInpDeg, double winSpdInp, double curnDirnInpDeg, double curnSpdInp);

    ShuiDongLiJiSuan shuiDongLiJiSuan(double u0, double ur, double vr, double rRad, double uAcc, double vAcc, double rAcc);

    PropRudder propRudder(double u,double v,double rRad,double derta);

    WaveForce waveForce(double faiDeg,double waveNmda,double waveDirnInpDeg,double waveHeight);

    CurrentRelative currentRelative(double u,double v,double faiDeg,double curnDirnInpDeg,double curnSpdInp);

    WindForce windForce(double u,double v,double faiDeg,double winDirnInpDeg,double winSpdInp);

    LonsLatsTrans lonsLatsTrans(List<Double[]> ptsDesiredArray, Boolean justStart);

    Boolean isCrossingMultipleOf30(double prevValue,double currentValue);

    DoublePropRudderChange doublePropRudderChange(double ur,double vr,double rRad,double n,double dertaDeg);


}
