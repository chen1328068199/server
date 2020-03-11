package com.stan.server.controller;

import com.google.zxing.WriterException;
import com.stan.server.service.AttendanceService;
import com.stan.server.utils.QRCodeUtils;
import com.stan.server.utils.SecurityAuthUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("attendance")
@Slf4j
@Api(tags = "考勤接口")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;


    @ApiOperation("发起考勤")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "longitude", value = "经度", required = true, dataType = "String"),
            @ApiImplicitParam(name = "latitude", value = "纬度", required = true, dataType = "String"),
    })
    public void startAttendance(@RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude, @RequestParam("latitude") int distance) {

    }

    @GetMapping("QRCode")
    @ApiOperation("获取二维码")
    public void getQRCode(HttpServletResponse resp) {
        // 生成二维码图像流输出到Servlet输出流中。
        try (ServletOutputStream outputStream = resp.getOutputStream()) {
            byte[] bytes = QRCodeUtils.generateQRCodeImageStream("", 1080, 1080);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            BufferedImage image = ImageIO.read(byteArrayInputStream);

            resp.setHeader("Pragma", "no-cache");
            resp.setHeader("Cache-Control", "no-cache");
            resp.setDateHeader("Expires", 0);
            ImageIO.write(image, "png", outputStream);

        } catch (IOException | WriterException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("scanQRCode")
    @ApiOperation("二维码考勤")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "二维码内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "longitude", value = "经度", required = true, dataType = "String"),
            @ApiImplicitParam(name = "latitude", value = "纬度", required = true, dataType = "String")
    })
    public void qrCodeAttendance(@RequestParam("key") String key, @RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude) {
        String attendanceKey = attendanceService.getQRCodeAttendanceKey();
        if (key.equals(attendanceKey)) {
            // TODO 校验地理位置是否合法
            // TODO 记录用户考勤
            int userId = SecurityAuthUtil.getLoginId();

        }
    }
}
