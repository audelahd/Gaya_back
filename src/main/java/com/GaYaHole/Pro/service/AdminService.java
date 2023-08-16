package com.GaYaHole.Pro.service;

import com.GaYaHole.Pro.entity.Notice;
import com.GaYaHole.Pro.entity.User;
import java.util.List;

public interface AdminService {

    public String totalProfit() throws Exception; //총 매출
    public void addNotice(Notice notice) throws Exception; //공지 추가
    public List userinfo() throws Exception;
    public List allreservation() throws Exception; //전체 예약 조회
    public void modUsergrade(User user) throws Exception; //사용자 등급 조정
    public List allrooms() throws Exception; //전체 방 조회
    public List allNotice() throws Exception; // 공지 조회 (전체)
    public void modNotice(Notice notice) throws Exception; // 공지 수정
    public void delNotice(int n_num) throws Exception; // 공지 삭제

}
