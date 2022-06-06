package com.ading_keki.stealthy_striver.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @project:stealthy_striver
 * @Created-Time:2022/5/31 20:26
 * Author: medi
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SsRecord {
    private int ssId;
    private int ssUserId;
    private String loginIp;
    private String target;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private int duraiton;
    private byte status;

    @Override
    public String toString() {
        return "SsRecord{" +
                "ssId=" + ssId +
                ", ssUserId=" + ssUserId +
                ", loginIp='" + loginIp + '\'' +
                ", target='" + target + '\'' +
                ", createTime=" + createTime +
                ", duraiton=" + duraiton +
                ", status=" + status +
                '}';
    }
}
