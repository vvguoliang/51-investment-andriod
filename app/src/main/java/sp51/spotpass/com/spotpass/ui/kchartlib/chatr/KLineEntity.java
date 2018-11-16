package sp51.spotpass.com.spotpass.ui.kchartlib.chatr;

import sp51.spotpass.com.spotpass.ui.kchartlib.entity.IKLine;


/**
 * K线实体
 * Created by tifezh on 2016/5/16.
 */

public class KLineEntity implements IKLine {


    public String Date;
    public float Open;
    public float High;
    public float Low;
    public float Close;
    public float Volume;

    public float MA5Price;

    public float MA10Price;

    public float MA20Price;

    public float dea;

    public float dif;

    public float macd;

    public float k;

    public float d;

    public float j;

    public float rsi1;

    public float rsi2;

    public float rsi3;

    public float up;

    public float mb;

    public float dn;

    public float MA5Volume;

    public float MA10Volume;


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public float getOpen() {
        return Open;
    }

    public void setOpen(float open) {
        Open = open;
    }

    public float getHigh() {
        return High;
    }

    public void setHigh(float high) {
        High = high;
    }

    public float getLow() {
        return Low;
    }

    public void setLow(float low) {
        Low = low;
    }

    public float getClose() {
        return Close;
    }

    public void setClose(float close) {
        Close = close;
    }

    @Override
    public float getVolume() {
        return Volume;
    }

    public void setVolume(float volume) {
        Volume = volume;
    }

    @Override
    public float getMA5Price() {
        return MA5Price;
    }

    public void setMA5Price(float MA5Price) {
        this.MA5Price = MA5Price;
    }

    @Override
    public float getMA10Price() {
        return MA10Price;
    }

    public void setMA10Price(float MA10Price) {
        this.MA10Price = MA10Price;
    }

    @Override
    public float getMA20Price() {
        return MA20Price;
    }

    public void setMA20Price(float MA20Price) {
        this.MA20Price = MA20Price;
    }

    @Override
    public float getDea() {
        return dea;
    }

    public void setDea(float dea) {
        this.dea = dea;
    }

    @Override
    public float getDif() {
        return dif;
    }

    public void setDif(float dif) {
        this.dif = dif;
    }

    @Override
    public float getMacd() {
        return macd;
    }

    public void setMacd(float macd) {
        this.macd = macd;
    }

    @Override
    public float getK() {
        return k;
    }

    public void setK(float k) {
        this.k = k;
    }

    @Override
    public float getD() {
        return d;
    }

    public void setD(float d) {
        this.d = d;
    }

    @Override
    public float getJ() {
        return j;
    }

    public void setJ(float j) {
        this.j = j;
    }

    @Override
    public float getRsi1() {
        return rsi1;
    }

    public void setRsi1(float rsi1) {
        this.rsi1 = rsi1;
    }

    @Override
    public float getRsi2() {
        return rsi2;
    }

    public void setRsi2(float rsi2) {
        this.rsi2 = rsi2;
    }

    @Override
    public float getRsi3() {
        return rsi3;
    }

    public void setRsi3(float rsi3) {
        this.rsi3 = rsi3;
    }

    @Override
    public float getUp() {
        return up;
    }

    public void setUp(float up) {
        this.up = up;
    }

    @Override
    public float getMb() {
        return mb;
    }

    public void setMb(float mb) {
        this.mb = mb;
    }

    @Override
    public float getDn() {
        return dn;
    }

    public void setDn(float dn) {
        this.dn = dn;
    }

    @Override
    public float getMA5Volume() {
        return MA5Volume;
    }

    public void setMA5Volume(float MA5Volume) {
        this.MA5Volume = MA5Volume;
    }

    @Override
    public float getMA10Volume() {
        return MA10Volume;
    }

    public void setMA10Volume(float MA10Volume) {
        this.MA10Volume = MA10Volume;
    }

    @Override
    public float getOpenPrice() {
        return Open;
    }

    @Override
    public float getHighPrice() {
        return High;
    }

    @Override
    public float getLowPrice() {
        return Low;
    }

    @Override
    public float getClosePrice() {
        return Close;
    }
}
