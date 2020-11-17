package hello.controller;

import git.eftp.TransferServer;
import git.eftp.vo.TransResVO;

/**
 * @创建人 sgwang
 * @name EftpClientTest
 * @user shiguang.wang
 * @创建时间 2020/11/11
 * @描述
 */
public class EftpClientTest {

    public static void main(String[] args) {
        TransferServer transFtp = new TransferServer("32.114.72.178", 9002);
        //下载前先判断.OK文件是否存在
        TransResVO isok  = transFtp.fileTransForm ("trans", "down", "/home/eftp/abc.txt", "logFile_20201111.tar.gz", "0111", "01", "");
        String retCode = isok.getRetCode();
        System.out.print(retCode + isok.getRetMsg());
    }

}
