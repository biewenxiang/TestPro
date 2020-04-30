package com.bwx.main;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {


        String aa = "<div id=\"7d\" class=\"c7d\">\n" +
                "\t\t\t\t\t\t\t\t<input type=\"hidden\" id=\"hidden_title\" value=\"07月26日08时 周五  晴  36/25°C\">\n" +
                "<input type=\"hidden\" id=\"fc_24h_internal_update_time\" value=\"2019072608\">\n" +
                "<input type=\"hidden\" id=\"update_time\" value=\"11:30\">\n" +
                "<ul class=\"t clearfix\">\n" +
                "<li class=\"sky skyid lv1 on\">\n" +
                "<h1>26日（今天）</h1>\n" +
                "<big class=\"png40 d00\"></big>\n" +
                "<big class=\"png40 n00\"></big>\n" +
                "<p title=\"晴\" class=\"wea\">晴</p>\n" +
                "<p class=\"tem\">\n" +
                "<span>36</span>/<i>25℃</i>\n" +
                "</p>\n" +
                "<p class=\"win\">\n" +
                "<em>\n" +
                "<span title=\"南风\" class=\"S\"></span>\n" +
                "<span title=\"北风\" class=\"N\"></span>\n" +
                "</em>\n" +
                "<i>&lt;3级</i>\n" +
                "</p>\n" +
                "<div class=\"slid\"></div>\n" +
                "</li>\n" +
                "<li class=\"sky skyid lv1\">\n" +
                "<h1>27日（明天）</h1>\n" +
                "<big class=\"png40 d01\"></big>\n" +
                "<big class=\"png40 n01\"></big>\n" +
                "<p title=\"多云\" class=\"wea\">多云</p>\n" +
                "<p class=\"tem\">\n" +
                "<span>37</span>/<i>26℃</i>\n" +
                "</p>\n" +
                "<p class=\"win\">\n" +
                "<em>\n" +
                "<span title=\"西南风\" class=\"SW\"></span>\n" +
                "<span title=\"东北风\" class=\"NE\"></span>\n" +
                "</em>\n" +
                "<i>&lt;3级</i>\n" +
                "</p>\n" +
                "<div class=\"slid\"></div>\n" +
                "</li>\n" +
                "<li class=\"sky skyid lv1\">\n" +
                "<h1>28日（后天）</h1>\n" +
                "<big class=\"png40 d01\"></big>\n" +
                "<big class=\"png40 n08\"></big>\n" +
                "<p title=\"多云转中雨\" class=\"wea\">多云转中雨</p>\n" +
                "<p class=\"tem\">\n" +
                "<span>34</span>/<i>25℃</i>\n" +
                "</p>\n" +
                "<p class=\"win\">\n" +
                "<em>\n" +
                "<span title=\"南风\" class=\"S\"></span>\n" +
                "<span title=\"南风\" class=\"S\"></span>\n" +
                "</em>\n" +
                "<i>&lt;3级</i>\n" +
                "</p>\n" +
                "<div class=\"slid\"></div>\n" +
                "</li>\n" +
                "<li class=\"sky skyid lv3\">\n" +
                "<h1>29日（周一）</h1>\n" +
                "<big class=\"png40 d08\"></big>\n" +
                "<big class=\"png40 n01\"></big>\n" +
                "<p title=\"中雨转多云\" class=\"wea\">中雨转多云</p>\n" +
                "<p class=\"tem\">\n" +
                "<span>29</span>/<i>22℃</i>\n" +
                "</p>\n" +
                "<p class=\"win\">\n" +
                "<em>\n" +
                "<span title=\"西北风\" class=\"NW\"></span>\n" +
                "<span title=\"西风\" class=\"W\"></span>\n" +
                "</em>\n" +
                "<i>&lt;3级</i>\n" +
                "</p>\n" +
                "<div class=\"slid\"></div>\n" +
                "</li>\n" +
                "<li class=\"sky skyid lv2\">\n" +
                "<h1>30日（周二）</h1>\n" +
                "<big class=\"png40 d01\"></big>\n" +
                "<big class=\"png40 n00\"></big>\n" +
                "<p title=\"多云转晴\" class=\"wea\">多云转晴</p>\n" +
                "<p class=\"tem\">\n" +
                "<span>35</span>/<i>21℃</i>\n" +
                "</p>\n" +
                "<p class=\"win\">\n" +
                "<em>\n" +
                "<span title=\"南风\" class=\"S\"></span>\n" +
                "<span title=\"北风\" class=\"N\"></span>\n" +
                "</em>\n" +
                "<i>&lt;3级</i>\n" +
                "</p>\n" +
                "<div class=\"slid\"></div>\n" +
                "</li>\n" +
                "<li class=\"sky skyid lv2\">\n" +
                "<h1>31日（周三）</h1>\n" +
                "<big class=\"png40 d01\"></big>\n" +
                "<big class=\"png40 n01\"></big>\n" +
                "<p title=\"多云\" class=\"wea\">多云</p>\n" +
                "<p class=\"tem\">\n" +
                "<span>35</span>/<i>22℃</i>\n" +
                "</p>\n" +
                "<p class=\"win\">\n" +
                "<em>\n" +
                "<span title=\"北风\" class=\"N\"></span>\n" +
                "<span title=\"北风\" class=\"N\"></span>\n" +
                "</em>\n" +
                "<i>&lt;3级</i>\n" +
                "</p>\n" +
                "<div class=\"slid\"></div>\n" +
                "</li>\n" +
                "<li class=\"sky skyid lv2\">\n" +
                "<h1>1日（周四）</h1>\n" +
                "<big class=\"png40 d00\"></big>\n" +
                "<big class=\"png40 n00\"></big>\n" +
                "<p title=\"晴\" class=\"wea\">晴</p>\n" +
                "<p class=\"tem\">\n" +
                "<span>35</span>/<i>25℃</i>\n" +
                "</p>\n" +
                "<p class=\"win\">\n" +
                "<em>\n" +
                "<span title=\"北风\" class=\"N\"></span>\n" +
                "<span title=\"北风\" class=\"N\"></span>\n" +
                "</em>\n" +
                "<i>&lt;3级</i>\n" +
                "</p>\n" +
                "<div class=\"slid\"></div>\n" +
                "</li>\n" +
                "</ul>\n" +
                "<i class=\"line1\"></i>\n" +
                "\t\t\t\t\t\t\t\t\n" +
                "\t\t\t<div class=\"btn\">\n" +
                "\t\t\t\t<em class=\"on\">分时段预报</em>\n" +
                "\t\t\t\t<em>生活指数</em>\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t<!--<p id=\"fb_sub\" class=\"fb_sub\">报告蓝天</p>-->\n" +
                "\t\t\t\t<div class=\"r\">\n" +
                "\t\t\t\t\t<span class=\"tit\">蓝天预报</span>\n" +
                "\t\t\t\t\t<i id=\"sky-on\" class=\"turn\">\n" +
                "\t\t\t\t\t\t<i></i>\n" +
                "\t\t\t\t\t</i>\n" +
                "\t\t\t\t\t<!--<i class=\"icon\"></i>-->\n" +
                "\t\t\t\t\t<div class=\"skypop\">\n" +
                "\t\t\t\t\t\t<h3>蓝天预报综合天气现象、能见度、空气质量等因子，预测未来一周的天空状况。</h3>\n" +
                "\t\t\t\t\t\t<ul>\n" +
                "\t\t\t\t\t\t\t<li class=\"lv1\">\n" +
                "\t\t\t\t\t\t\t\t<em></em><span>天空蔚蓝</span>\n" +
                "\t\t\t\t\t\t\t\t<b>可见透彻蓝天，或有蓝天白云美景。</b>\n" +
                "\t\t\t\t\t\t\t</li>\n" +
                "\t\t\t\t\t\t\t<li class=\"lv2\">\n" +
                "\t\t\t\t\t\t\t\t<em></em><span>天空淡蓝</span>\n" +
                "\t\t\t\t\t\t\t\t<b>天空不够清澈，以浅蓝色为主。</b>\n" +
                "\t\t\t\t\t\t\t</li>\n" +
                "\t\t\t\t\t\t\t<li class=\"lv3\">\n" +
                "\t\t\t\t\t\t\t\t<em></em><span>天空阴沉</span>\n" +
                "\t\t\t\t\t\t\t\t<b>阴天或有雨雪，天空灰暗。</b>\n" +
                "\t\t\t\t\t\t\t</li>\n" +
                "\t\t\t\t\t\t\t<li class=\"lv4\">\n" +
                "\t\t\t\t\t\t\t\t<em></em><span>天空灰霾</span>\n" +
                "\t\t\t\t\t\t\t\t<b>出现霾或沙尘，天空灰蒙浑浊。</b>\n" +
                "\t\t\t\t\t\t\t</li>\n" +
                "\t\t\t\t\t\t</ul>\n" +
                "\t\t\t\t\t\t<i class=\"s\"></i>\n" +
                "\t\t\t\t\t</div>\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t\t\n" +
                "\t\t\t\t\n" +
                "\t\t\t</div>\n" +
                "\t\t\t\t\t\t<input type=\"hidden\" id=\"fc_3h_internal_update_time\" value=\"2019-07-26 11:30\">\n" +
                "<div class=\"curve_livezs\" id=\"curve\">\n" +
                "<div class=\"time\">\n" +
                "<em style=\"width:85px;left:0px\">08时</em><em style=\"width:85px;left:85px\">11时</em><em style=\"width:85px;left:170px\">14时</em><em style=\"width:85px;left:255px\">17时</em><em style=\"width:85px;left:340px\">20时</em><em style=\"width:85px;left:425px\">23时</em><em style=\"width:85px;left:510px\">02时</em><em style=\"width:85px;left:595px\">05时</em></div>\n" +
                "<div class=\"wpic\">\n" +
                "<div style=\"width:85px;left:0px;top:30px\"><big title=\"晴\" class=\"png40 d00 lv1\"></big></div><div style=\"width:85px;left:85px;top:30px\"><big title=\"晴\" class=\"png40 d00 lv1\"></big></div><div style=\"width:85px;left:170px;top:30px\"><big title=\"晴\" class=\"png40 d00 lv1\"></big></div><div style=\"width:85px;left:255px;top:30px\"><big title=\"晴\" class=\"png40 d00 lv1\"></big></div><div style=\"width:85px;left:340px;top:30px\"><big title=\"晴\" class=\"png40 n00 lv0\"></big></div><div style=\"width:85px;left:425px;top:30px\"><big title=\"晴\" class=\"png40 n00 lv0\"></big></div><div style=\"width:85px;left:510px;top:30px\"><big title=\"晴\" class=\"png40 n00 lv0\"></big></div><div style=\"width:85px;left:595px;top:30px\"><big title=\"晴\" class=\"png40 n00 lv0\"></big></div></div>\n" +
                "<div id=\"biggt\" class=\"biggt\"><svg height=\"70\" version=\"1.1\" width=\"680\" xmlns=\"http://www.w3.org/2000/svg\" style=\"overflow: hidden; position: relative; left: -0.6px;\"><desc style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\">Created with Raphaël 2.1.2</desc><defs style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\"></defs><path fill=\"none\" stroke=\"#f68227\" d=\"M42.5,49L127.5,24L212.5,9L297.5,14L382.5,34L467.5,44L552.5,59L637.5,59\" stroke-width=\"2\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\"></path><circle cx=\"42.5\" cy=\"49\" r=\"4\" fill=\"#f68227\" stroke=\"#f68227\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\"></circle><circle cx=\"127.5\" cy=\"24\" r=\"4\" fill=\"#f68227\" stroke=\"#f68227\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\"></circle><circle cx=\"212.5\" cy=\"9\" r=\"4\" fill=\"#f68227\" stroke=\"#f68227\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\"></circle><circle cx=\"297.5\" cy=\"14\" r=\"4\" fill=\"#f68227\" stroke=\"#f68227\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\"></circle><circle cx=\"382.5\" cy=\"34\" r=\"4\" fill=\"#f68227\" stroke=\"#f68227\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\"></circle><circle cx=\"467.5\" cy=\"44\" r=\"4\" fill=\"#f68227\" stroke=\"#f68227\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\"></circle><circle cx=\"552.5\" cy=\"59\" r=\"4\" fill=\"#f68227\" stroke=\"#f68227\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\"></circle><circle cx=\"637.5\" cy=\"59\" r=\"4\" fill=\"#f68227\" stroke=\"#f68227\" style=\"-webkit-tap-highlight-color: rgba(0, 0, 0, 0);\"></circle></svg>\n" +
                "</div>\n" +
                "<div class=\"tem\">\n" +
                "<em style=\"width:85px;left:0px;top:119px\">28℃</em><em style=\"width:85px;left:85px;top:94px\">33℃</em><em style=\"width:85px;left:170px;top:79px\">36℃</em><em style=\"width:85px;left:255px;top:84px\">35℃</em><em style=\"width:85px;left:340px;top:104px\">31℃</em><em style=\"width:85px;left:425px;top:114px\">29℃</em><em style=\"width:85px;left:510px;top:129px\">26℃</em><em style=\"width:85px;left:595px;top:129px\">26℃</em></div>\n" +
                "<div class=\"winf\">\n" +
                "<em style=\"width:85px;left:0px\">南风</em><em style=\"width:85px;left:85px\">南风</em><em style=\"width:85px;left:170px\">南风</em><em style=\"width:85px;left:255px\">南风</em><em style=\"width:85px;left:340px\">南风</em><em style=\"width:85px;left:425px\">北风</em><em style=\"width:85px;left:510px\">北风</em><em style=\"width:85px;left:595px\">北风</em></div>\n" +
                "<div class=\"winl\">\n" +
                "<em style=\"width:85px;left:0px\">&lt;3级</em><em style=\"width:85px;left:85px\">&lt;3级</em><em style=\"width:85px;left:170px\">&lt;3级</em><em style=\"width:85px;left:255px\">&lt;3级</em><em style=\"width:85px;left:340px\">&lt;3级</em><em style=\"width:85px;left:425px\">&lt;3级</em><em style=\"width:85px;left:510px\">&lt;3级</em><em style=\"width:85px;left:595px\">&lt;3级</em></div>\n" +
                "</div>\n" +
                "<script>\n" +
                "var hour3data={\"1d\":[\"26日08时,d00,晴,28℃,南风,<3级,1\",\"26日11时,d00,晴,33℃,南风,<3级,1\",\"26日14时,d00,晴,36℃,南风,<3级,1\",\"26日17时,d00,晴,35℃,南风,<3级,1\",\"26日20时,n00,晴,31℃,南风,<3级,0\",\"26日23时,n00,晴,29℃,北风,<3级,0\",\"27日02时,n00,晴,26℃,北风,<3级,0\",\"27日05时,n00,晴,26℃,北风,<3级,0\",\"27日08时,d00,晴,29℃,北风,<3级,1\"],\"23d\":[[\"27日08时,d00,晴,29℃,北风,<3级,1\",\"27日11时,d00,晴,33℃,西南风,<3级,1\",\"27日14时,d00,晴,35℃,西南风,<3级,1\",\"27日17时,d01,多云,34℃,西南风,<3级,1\",\"27日20时,n01,多云,32℃,西南风,<3级,0\",\"27日23时,n00,晴,30℃,东北风,<3级,0\",\"28日02时,n00,晴,28℃,东北风,<3级,0\",\"28日05时,n00,晴,27℃,东北风,<3级,0\"],[\"28日08时,d01,多云,28℃,东北风,<3级,1\",\"28日11时,d01,多云,32℃,南风,<3级,1\",\"28日14时,d01,多云,33℃,南风,<3级,1\",\"28日17时,d01,多云,33℃,南风,<3级,1\",\"28日20时,n01,多云,31℃,南风,<3级,0\",\"28日23时,n08,中雨,28℃,南风,<3级,0\",\"29日02时,n07,小雨,27℃,南风,<3级,0\",\"29日05时,n08,中雨,27℃,南风,<3级,0\"]],\"7d\":[[\"26日08时,d00,晴,28℃,南风,<3级,1\",\"26日11时,d00,晴,33℃,南风,<3级,1\",\"26日14时,d00,晴,36℃,南风,<3级,1\",\"26日17时,d00,晴,35℃,南风,<3级,1\",\"26日20时,n00,晴,31℃,南风,<3级,0\",\"26日23时,n00,晴,29℃,北风,<3级,0\",\"27日02时,n00,晴,26℃,北风,<3级,0\",\"27日05时,n00,晴,26℃,北风,<3级,0\"],[\"27日08时,d00,晴,29℃,北风,<3级,1\",\"27日11时,d00,晴,33℃,西南风,<3级,1\",\"27日14时,d00,晴,35℃,西南风,<3级,1\",\"27日17时,d01,多云,34℃,西南风,<3级,1\",\"27日20时,n01,多云,32℃,西南风,<3级,0\",\"27日23时,n00,晴,30℃,东北风,<3级,0\",\"28日02时,n00,晴,28℃,东北风,<3级,0\",\"28日05时,n00,晴,27℃,东北风,<3级,0\"],[\"28日08时,d01,多云,28℃,东北风,<3级,1\",\"28日11时,d01,多云,32℃,南风,<3级,1\",\"28日14时,d01,多云,33℃,南风,<3级,1\",\"28日17时,d01,多云,33℃,南风,<3级,1\",\"28日20时,n01,多云,31℃,南风,<3级,0\",\"28日23时,n08,中雨,28℃,南风,<3级,0\",\"29日02时,n07,小雨,27℃,南风,<3级,0\",\"29日05时,n08,中雨,27℃,南风,<3级,0\"],[\"29日08时,d02,阴,26℃,南风,<3级,3\",\"29日14时,d02,阴,26℃,西北风,<3级,3\",\"29日20时,n09,大雨,26℃,西北风,<3级,0\",\"30日02时,n01,多云,24℃,西风,<3级,0\"],[\"30日08时,d01,多云,25℃,西风,<3级,2\",\"30日14时,d01,多云,32℃,南风,<3级,2\",\"30日20时,n00,晴,28℃,南风,<3级,0\",\"31日02时,n00,晴,24℃,北风,<3级,0\"],[\"31日08时,d00,晴,25℃,北风,<3级,2\",\"31日14时,d00,晴,32℃,北风,<3级,2\",\"31日20时,n01,多云,28℃,北风,<3级,0\",\"01日02时,n01,多云,24℃,北风,<3级,0\"],[\"01日08时,d01,多云,26℃,北风,<3级,3\",\"01日14时,d00,晴,32℃,北风,<3级,2\",\"01日20时,n00,晴,30℃,北风,<3级,0\",\"02日02时,n00,晴,27℃,北风,<3级,0\"]]}\n" +
                "</script>\n" +
                "\t\t                 \n" +
                "\t\t\t<div id=\"livezs\" class=\"livezs curve_livezs\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<script src=\"http://i.tq121.com.cn/j/weather2015/pagefilp.js\" type=\"text/javascript\"></script>\n" +
                "<style>\n" +
                ".pageflip {right: 0px; float: right; position: relative; top: 0px}\n" +
                " .pageflip IMG {z-index: 99; right: -3px; width: 30px; position: absolute; top: -1px; height: 30px; ms-interpolation-mode: bicubic}\n" +
                " .pageflip .msg_block {right: 0px; background: url(http://i.tq121.com.cn/i/weather2015/png/subscribe.png) no-repeat right top; overflow: hidden; width: 25px; position: absolute; top: 0px; height: 25px}\n" +
                "</style>\n" +
                "<input type=\"hidden\" id=\"zs_7d_update_time\" value=\"2019-07-26 12:00:00.0\">\n" +
                "<div class=\"hide show\">\n" +
                "<ul class=\"clearfix\">\n" +
                "<li class=\"li1\">\n" +
                "<i></i>\n" +
                "<span>很强</span>\n" +
                "<em>紫外线指数</em>\n" +
                "<p>涂擦SPF20以上，PA++护肤品，避强光。</p>\n" +
                "</li>\n" +
                "<li class=\"li2 hot\">\n" +
                "<a href=\"http://www.weather.com.cn/rw/\" target=\"_blank\">\n" +
                "<div class=\"pageflip\">\n" +
                "<img src=\"http://i.tq121.com.cn/i/weather2015/png/page_flip.png\">\n" +
                "<div class=\"msg_block\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<i></i>\n" +
                "<span>\n" +
                "<em class=\"star\"></em><em></em><em></em><em></em><em></em>\n" +
                "</span>\n" +
                "<em>减肥指数</em>\n" +
                "<p>天气有点热，运动多补水。</p>\n" +
                "</a>\n" +
                "</li>\n" +
                "<li class=\"li5\">\n" +
                "<i></i>\n" +
                "<span>易波动</span>\n" +
                "<em>健臻·血糖指数</em>\n" +
                "<p>气温高，血糖易波动，注意防暑降温。</p>\n" +
                "</li>\n" +
                "<li class=\"li3 hot\" id=\"chuanyi\">\n" +
                "<a href=\"http://www.weather.com.cn/forecast/ct.shtml?areaid=101010200\" target=\"_blank\">\n" +
                "<div class=\"pageflip\">\n" +
                "<img src=\"http://i.tq121.com.cn/i/weather2015/png/page_flip.png\">\n" +
                "<div class=\"msg_block\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<i></i>\n" +
                "<span>炎热</span>\n" +
                "<em>穿衣指数</em>\n" +
                "<p>建议穿短衫、短裤等清凉夏季服装。</p>\n" +
                "</a>\n" +
                "</li>\n" +
                "<li class=\"li4\">\n" +
                "<i></i>\n" +
                "<span>较适宜</span>\n" +
                "<em>洗车指数</em>\n" +
                "<p>无雨且风力较小，易保持清洁度。</p>\n" +
                "</li>\n" +
                "<li class=\"li6\">\n" +
                "<i></i>\n" +
                "<span>中</span>\n" +
                "<em>空气污染扩散指数</em>\n" +
                "<p>易感人群应适当减少室外活动。</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "<div class=\"hide\">\n" +
                "<ul class=\"clearfix\">\n" +
                "<li class=\"li1\">\n" +
                "<i></i>\n" +
                "<span>中等</span>\n" +
                "<em>紫外线指数</em>\n" +
                "<p>涂擦SPF大于15、PA+防晒护肤品。</p>\n" +
                "</li>\n" +
                "<li class=\"li2 hot\">\n" +
                "<a href=\"http://www.weather.com.cn/rw/\" target=\"_blank\">\n" +
                "<div class=\"pageflip\">\n" +
                "<img src=\"http://i.tq121.com.cn/i/weather2015/png/page_flip.png\">\n" +
                "<div class=\"msg_block\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<i></i>\n" +
                "<span>\n" +
                "<em class=\"star\"></em><em></em><em></em><em></em><em></em>\n" +
                "</span>\n" +
                "<em>减肥指数</em>\n" +
                "<p>天气有点热，运动多补水。</p>\n" +
                "</a>\n" +
                "</li>\n" +
                "<li class=\"li5\">\n" +
                "<i></i>\n" +
                "<span>易波动</span>\n" +
                "<em>健臻·血糖指数</em>\n" +
                "<p>气温高，血糖易波动，注意防暑降温。</p>\n" +
                "</li>\n" +
                "<li class=\"li3 hot\" id=\"chuanyi02\">\n" +
                "<a href=\"http://www.weather.com.cn/forecast/ct.shtml?areaid=101010200\" target=\"_blank\">\n" +
                "<div class=\"pageflip\">\n" +
                "<img src=\"http://i.tq121.com.cn/i/weather2015/png/page_flip.png\">\n" +
                "<div class=\"msg_block\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<i></i>\n" +
                "<span>炎热</span>\n" +
                "<em>穿衣指数</em>\n" +
                "<p>建议穿短衫、短裤等清凉夏季服装。</p>\n" +
                "</a>\n" +
                "</li>\n" +
                "<li class=\"li4\">\n" +
                "<i></i>\n" +
                "<span>较适宜</span>\n" +
                "<em>洗车指数</em>\n" +
                "<p>无雨且风力较小，易保持清洁度。</p>\n" +
                "</li>\n" +
                "<li class=\"li6\">\n" +
                "<i></i>\n" +
                "<span>中</span>\n" +
                "<em>空气污染扩散指数</em>\n" +
                "<p>易感人群应适当减少室外活动。</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "<div class=\"hide\">\n" +
                "<ul class=\"clearfix\">\n" +
                "<li class=\"li1\">\n" +
                "<i></i>\n" +
                "<span>中等</span>\n" +
                "<em>紫外线指数</em>\n" +
                "<p>涂擦SPF大于15、PA+防晒护肤品。</p>\n" +
                "</li>\n" +
                "<li class=\"li2 hot\">\n" +
                "<a href=\"http://www.weather.com.cn/rw/\" target=\"_blank\">\n" +
                "<div class=\"pageflip\">\n" +
                "<img src=\"http://i.tq121.com.cn/i/weather2015/png/page_flip.png\">\n" +
                "<div class=\"msg_block\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<i></i>\n" +
                "<span>\n" +
                "<em class=\"star\"></em><em></em><em></em><em></em><em></em>\n" +
                "</span>\n" +
                "<em>减肥指数</em>\n" +
                "<p>天气有点热，运动多补水。</p>\n" +
                "</a>\n" +
                "</li>\n" +
                "<li class=\"li5\">\n" +
                "<i></i>\n" +
                "<span>易波动</span>\n" +
                "<em>健臻·血糖指数</em>\n" +
                "<p>血糖易波动，注意监测。</p>\n" +
                "</li>\n" +
                "<li class=\"li3 hot\" id=\"chuanyi03\">\n" +
                "<a href=\"http://www.weather.com.cn/forecast/ct.shtml?areaid=101010200\" target=\"_blank\">\n" +
                "<div class=\"pageflip\">\n" +
                "<img src=\"http://i.tq121.com.cn/i/weather2015/png/page_flip.png\">\n" +
                "<div class=\"msg_block\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<i></i>\n" +
                "<span>炎热</span>\n" +
                "<em>穿衣指数</em>\n" +
                "<p>建议穿短衫、短裤等清凉夏季服装。</p>\n" +
                "</a>\n" +
                "</li>\n" +
                "<li class=\"li4\">\n" +
                "<i></i>\n" +
                "<span>不宜</span>\n" +
                "<em>洗车指数</em>\n" +
                "<p>有雨，雨水和泥水会弄脏爱车。</p>\n" +
                "</li>\n" +
                "<li class=\"li6\">\n" +
                "<i></i>\n" +
                "<span>中</span>\n" +
                "<em>空气污染扩散指数</em>\n" +
                "<p>易感人群应适当减少室外活动。</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "<div class=\"hide\">\n" +
                "<ul class=\"clearfix\">\n" +
                "<li class=\"li1\">\n" +
                "<i></i>\n" +
                "<span>最弱</span>\n" +
                "<em>紫外线指数</em>\n" +
                "<p>辐射弱，涂擦SPF8-12防晒护肤品。</p>\n" +
                "</li>\n" +
                "<li class=\"li2 hot\">\n" +
                "<a href=\"http://www.weather.com.cn/rw/\" target=\"_blank\">\n" +
                "<div class=\"pageflip\">\n" +
                "<img src=\"http://i.tq121.com.cn/i/weather2015/png/page_flip.png\">\n" +
                "<div class=\"msg_block\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<i></i>\n" +
                "<span>\n" +
                "<em class=\"star\"></em><em></em><em></em><em></em><em></em>\n" +
                "</span>\n" +
                "<em>减肥指数</em>\n" +
                "<p>夏天肉难藏，雨天坚持室内运动吧。</p>\n" +
                "</a>\n" +
                "</li>\n" +
                "<li class=\"li5\">\n" +
                "<i></i>\n" +
                "<span>易波动</span>\n" +
                "<em>健臻·血糖指数</em>\n" +
                "<p>气温多变，血糖易波动，请注意监测。</p>\n" +
                "</li>\n" +
                "<li class=\"li3 hot\" id=\"chuanyi04\">\n" +
                "<a href=\"http://www.weather.com.cn/forecast/ct.shtml?areaid=101010200\" target=\"_blank\">\n" +
                "<div class=\"pageflip\">\n" +
                "<img src=\"http://i.tq121.com.cn/i/weather2015/png/page_flip.png\">\n" +
                "<div class=\"msg_block\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<i></i>\n" +
                "<span>热</span>\n" +
                "<em>穿衣指数</em>\n" +
                "<p>适合穿T恤、短薄外套等夏季服装。</p>\n" +
                "</a>\n" +
                "</li>\n" +
                "<li class=\"li4\">\n" +
                "<i></i>\n" +
                "<span>不宜</span>\n" +
                "<em>洗车指数</em>\n" +
                "<p>有雨，雨水和泥水会弄脏爱车。</p>\n" +
                "</li>\n" +
                "<li class=\"li6\">\n" +
                "<i></i>\n" +
                "<span>优</span>\n" +
                "<em>空气污染扩散指数</em>\n" +
                "<p>气象条件非常有利于空气污染物扩散。</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "<div class=\"hide\">\n" +
                "<ul class=\"clearfix\">\n" +
                "<li class=\"li1\">\n" +
                "<i></i>\n" +
                "<span>中等</span>\n" +
                "<em>紫外线指数</em>\n" +
                "<p>涂擦SPF大于15、PA+防晒护肤品。</p>\n" +
                "</li>\n" +
                "<li class=\"li2 hot\">\n" +
                "<a href=\"http://www.weather.com.cn/rw/\" target=\"_blank\">\n" +
                "<div class=\"pageflip\">\n" +
                "<img src=\"http://i.tq121.com.cn/i/weather2015/png/page_flip.png\">\n" +
                "<div class=\"msg_block\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<i></i>\n" +
                "<span>\n" +
                "<em class=\"star\"></em><em></em><em></em><em></em><em></em>\n" +
                "</span>\n" +
                "<em>减肥指数</em>\n" +
                "<p>天气有点热，运动多补水。</p>\n" +
                "</a>\n" +
                "</li>\n" +
                "<li class=\"li5\">\n" +
                "<i></i>\n" +
                "<span>易波动</span>\n" +
                "<em>健臻·血糖指数</em>\n" +
                "<p>气温多变，血糖易波动，请注意监测。</p>\n" +
                "</li>\n" +
                "<li class=\"li3 hot\" id=\"chuanyi05\">\n" +
                "<a href=\"http://www.weather.com.cn/forecast/ct.shtml?areaid=101010200\" target=\"_blank\">\n" +
                "<div class=\"pageflip\">\n" +
                "<img src=\"http://i.tq121.com.cn/i/weather2015/png/page_flip.png\">\n" +
                "<div class=\"msg_block\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<i></i>\n" +
                "<span>炎热</span>\n" +
                "<em>穿衣指数</em>\n" +
                "<p>建议穿短衫、短裤等清凉夏季服装。</p>\n" +
                "</a>\n" +
                "</li>\n" +
                "<li class=\"li4\">\n" +
                "<i></i>\n" +
                "<span>不宜</span>\n" +
                "<em>洗车指数</em>\n" +
                "<p>积水较多，车辆易溅上泥水。</p>\n" +
                "</li>\n" +
                "<li class=\"li6\">\n" +
                "<i></i>\n" +
                "<span>中</span>\n" +
                "<em>空气污染扩散指数</em>\n" +
                "<p>易感人群应适当减少室外活动。</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "<div class=\"hide\">\n" +
                "<ul class=\"clearfix\">\n" +
                "<li class=\"li1\">\n" +
                "<i></i>\n" +
                "<span>中等</span>\n" +
                "<em>紫外线指数</em>\n" +
                "<p>涂擦SPF大于15、PA+防晒护肤品。</p>\n" +
                "</li>\n" +
                "<li class=\"li2 hot\">\n" +
                "<a href=\"http://www.weather.com.cn/rw/\" target=\"_blank\">\n" +
                "<div class=\"pageflip\">\n" +
                "<img src=\"http://i.tq121.com.cn/i/weather2015/png/page_flip.png\">\n" +
                "<div class=\"msg_block\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<i></i>\n" +
                "<span>\n" +
                "<em class=\"star\"></em><em></em><em></em><em></em><em></em>\n" +
                "</span>\n" +
                "<em>减肥指数</em>\n" +
                "<p>天气有点热，运动多补水。</p>\n" +
                "</a>\n" +
                "</li>\n" +
                "<li class=\"li5\">\n" +
                "<i></i>\n" +
                "<span>易波动</span>\n" +
                "<em>健臻·血糖指数</em>\n" +
                "<p>血糖易波动，注意监测。</p>\n" +
                "</li>\n" +
                "<li class=\"li3 hot\" id=\"chuanyi06\">\n" +
                "<a href=\"http://www.weather.com.cn/forecast/ct.shtml?areaid=101010200\" target=\"_blank\">\n" +
                "<div class=\"pageflip\">\n" +
                "<img src=\"http://i.tq121.com.cn/i/weather2015/png/page_flip.png\">\n" +
                "<div class=\"msg_block\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<i></i>\n" +
                "<span>炎热</span>\n" +
                "<em>穿衣指数</em>\n" +
                "<p>建议穿短衫、短裤等清凉夏季服装。</p>\n" +
                "</a>\n" +
                "</li>\n" +
                "<li class=\"li4\">\n" +
                "<i></i>\n" +
                "<span>适宜</span>\n" +
                "<em>洗车指数</em>\n" +
                "<p>天气较好，适合擦洗汽车。</p>\n" +
                "</li>\n" +
                "<li class=\"li6\">\n" +
                "<i></i>\n" +
                "<span>中</span>\n" +
                "<em>空气污染扩散指数</em>\n" +
                "<p>易感人群应适当减少室外活动。</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "<div class=\"hide\">\n" +
                "<ul class=\"clearfix\">\n" +
                "<li class=\"li1\">\n" +
                "<i></i>\n" +
                "<span>很强</span>\n" +
                "<em>紫外线指数</em>\n" +
                "<p>涂擦SPF20以上，PA++护肤品，避强光。</p>\n" +
                "</li>\n" +
                "<li class=\"li2 hot\">\n" +
                "<a href=\"http://www.weather.com.cn/rw/\" target=\"_blank\">\n" +
                "<div class=\"pageflip\">\n" +
                "<img src=\"http://i.tq121.com.cn/i/weather2015/png/page_flip.png\">\n" +
                "<div class=\"msg_block\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<i></i>\n" +
                "<span>\n" +
                "<em class=\"star\"></em><em></em><em></em><em></em><em></em>\n" +
                "</span>\n" +
                "<em>减肥指数</em>\n" +
                "<p>天气有点热，运动多补水。</p>\n" +
                "</a>\n" +
                "</li>\n" +
                "<li class=\"li5\">\n" +
                "<i></i>\n" +
                "<span>易波动</span>\n" +
                "<em>健臻·血糖指数</em>\n" +
                "<p>血糖易波动，注意监测。</p>\n" +
                "</li>\n" +
                "<li class=\"li3 hot\" id=\"chuanyi07\">\n" +
                "<a href=\"http://www.weather.com.cn/forecast/ct.shtml?areaid=101010200\" target=\"_blank\">\n" +
                "<div class=\"pageflip\">\n" +
                "<img src=\"http://i.tq121.com.cn/i/weather2015/png/page_flip.png\">\n" +
                "<div class=\"msg_block\">\n" +
                "</div>\n" +
                "</div>\n" +
                "<i></i>\n" +
                "<span>炎热</span>\n" +
                "<em>穿衣指数</em>\n" +
                "<p>建议穿短衫、短裤等清凉夏季服装。</p>\n" +
                "</a>\n" +
                "</li>\n" +
                "<li class=\"li4\">\n" +
                "<i></i>\n" +
                "<span>较适宜</span>\n" +
                "<em>洗车指数</em>\n" +
                "<p>无雨且风力较小，易保持清洁度。</p>\n" +
                "</li>\n" +
                "<li class=\"li6\">\n" +
                "<i></i>\n" +
                "<span>中</span>\n" +
                "<em>空气污染扩散指数</em>\n" +
                "<p>易感人群应适当减少室外活动。</p>\n" +
                "</li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "\t\t\t\t\t\t\t</div>\n" +
                "\t\t</div>";
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        long start = System.currentTimeMillis();

        for (int i = 1; i < 50000; i++) {
//            for (int i2 =1;i2<10;i++)
            int finalI = i;
//            2:18053 86370
//            1:19497 82808
//            3:17043 76750
            writeFile("/ser/iotest/1/" + finalI + ".txt", aa);

//            Runnable worker = new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            };
//            executorService.submit(worker);

        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        System.out.println("Finished all threads");
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("end");

    }

    public static boolean writeFile(String filePath, String content) {

        File file = new File(filePath);
        FileOutputStream output = null;
        OutputStreamWriter osw = null;
        boolean writeFlag = false;
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            String contentStr = content.replaceAll("(?<=>)\\s+|\\s+(?=<)", "\n").trim();
//            177065 50968 52308

//            String contentStr = content.replaceAll("\\s+<", "\n<").replaceAll(">\\s+", ">\n").trim();
            if (contentStr != null && !contentStr.isEmpty()) {
                output = new FileOutputStream(file);
                osw = new OutputStreamWriter(new BufferedOutputStream(output), "utf-8");
                osw.write(contentStr);
                osw.flush();
            }
            writeFlag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return writeFlag;
    }

}
