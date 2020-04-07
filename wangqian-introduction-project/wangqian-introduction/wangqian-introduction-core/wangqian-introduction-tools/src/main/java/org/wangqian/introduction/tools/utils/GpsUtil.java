package org.wangqian.introduction.tools.utils;

public class GpsUtil {
    private final static double PI = Math.PI;
    /*
     * 地球半径品均值
     */
    private final static double EARTH_RADIUS = 6371.393;


    /**
     * 根据经纬度和距离返回一个矩形范围
     *
     * @param lng      经度
     * @param lat      纬度
     * @param distance 距离(单位为米)
     * @return [lng1, lat1, lng2,lat2] 矩形的左下角(lng1,lat1)和右上角(lng2,lat2)
     */
    public static double[] getRectangle(double lng, double lat, long distance) {
        float delta = 111000;
        if (lng != 0 && lat != 0) {
            double lng1 = lng - distance
                    / Math.abs(Math.cos(Math.toRadians(lat)) * delta);
            double lng2 = lng + distance
                    / Math.abs(Math.cos(Math.toRadians(lat)) * delta);
            double lat1 = lat - (distance / delta);
            double lat2 = lat + (distance / delta);
            return new double[]{lng1, lat1, lng2, lat2};
        } else {
            // 等于0时的计算公式
            double lng1 = lng - distance / delta;
            double lng2 = lng + distance / delta;
            double lat1 = lat - (distance / delta);
            double lat2 = lat + (distance / delta);
            return new double[]{lng1, lat1, lng2, lat2};
        }
    }

    /**
     * 得到两点间的距离 米
     *
     * @param lat1 第一点纬度
     * @param lng1 第一点经度
     * @param lat2 第二点纬度
     * @param lng2 第二点经度
     * @return
     */
    public static double getDistanceOfMeter(double lat1, double lng1,
                                            double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10;
        return s;
    }

    private static double rad(double d) {
        return d * PI / 180.0;
    }


    /**
     * 根据经纬度和半径计算经纬度范围
     *
     * @param raidus 单位米
     * @return minLat, minLng, maxLat, maxLng
     */
    public static double[] getAround(double lat, double lon, int raidus) {
        Double latitude = lat;
        Double longitude = lon;
        Double degree = (24901 * 1609) / 360.0;
        double raidusMile = raidus;
        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;
        Double mpdLng = degree * Math.cos(latitude * (PI / 180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;
        return new double[]{minLat, minLng, maxLat, maxLng};
    }

    /**
     * @param longitudeA A点经度
     * @param latitudeA  A点纬度
     * @param longitudeB B点经度
     * @param latitudeB  B点纬度
     * @return 地球上两点的球面距离KM
     * @author WANGQI
     * @date 2019年3月21日 下午5:57:05
     */
    public static double getDistanceOfTwoPoints(double longitudeA, double latitudeA, double longitudeB, double latitudeB) {
        double xA = latitudeA * PI / 180;
        double xB = latitudeB * PI / 180;
        double yA = longitudeA * PI / 180;
        double yB = longitudeB * PI / 180;

        double $cl1 = Math.cos(xA);
        double $cl2 = Math.cos(xB);
        double $sl1 = Math.sin(xA);
        double $sl2 = Math.sin(xB);
        double $delta = yA - yB;
        double $cdelta = Math.cos($delta);
        double $sdelta = Math.sin($delta);

        double $y = Math.sqrt(Math.pow($cl2 * $sdelta, 2) + Math.pow($cl1 * $sl2 - $sl1 * $cl2 * $cdelta, 2));
        double $x = $sl1 * $sl2 + $cl1 * $cl2 * $cdelta;

        double $ad = Math.atan($y / $x);

        return $ad * EARTH_RADIUS;
    }

}