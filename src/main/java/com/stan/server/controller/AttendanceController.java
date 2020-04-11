package com.stan.server.controller;

import com.google.zxing.WriterException;
import com.stan.server.service.AttendanceService;
import com.stan.server.utils.*;
import io.swagger.annotations.Api;
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

    @GetMapping("QRCode")
    @ApiOperation("获取二维码")
    public void getQRCode(HttpServletResponse resp) {
        String attendanceCacheKey = "QRCodeKey";
        String qrCode = attendanceService.updateQRCodeAttendanceKey(attendanceCacheKey, 13);
        // 生成二维码图像流输出到Servlet输出流中。
        try (ServletOutputStream outputStream = resp.getOutputStream()) {
            byte[] bytes = QRCodeUtils.generateQRCodeImageStream(qrCode, 1080, 1080);
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
}
