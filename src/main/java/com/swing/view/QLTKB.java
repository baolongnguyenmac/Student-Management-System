package com.swing.view;

import com.swing.controller.ChuyenManHinh;
import com.swing.obj.DanhMuc;
import java.util.*;

public class QLTKB extends javax.swing.JPanel {

    public QLTKB() {
        initComponents();

        ChuyenManHinh controller = new ChuyenManHinh(ContentQLTKB);
        controller.setView(AddTKB, AddTKBLabel, "AddTKB");

        List<DanhMuc> listDanhMuc = new ArrayList<>();
        listDanhMuc.add(new DanhMuc("AddTKB", AddTKB, AddTKBLabel));
        listDanhMuc.add(new DanhMuc("XemTKBSinhVien", XemTKBSinhVien, XemTKBSinhVienLabel));
        listDanhMuc.add(new DanhMuc("XemTKBLop", XemTKBLop, XemTKBLopLabel));

        controller.setEvent(listDanhMuc);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        TabQLTKB = new javax.swing.JPanel();
        XemTKBLop = new javax.swing.JPanel();
        XemTKBLopLabel = new javax.swing.JLabel();
        AddTKB = new javax.swing.JPanel();
        AddTKBLabel = new javax.swing.JLabel();
        XemTKBSinhVien = new javax.swing.JPanel();
        XemTKBSinhVienLabel = new javax.swing.JLabel();
        ContentQLTKB = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        TabQLTKB.setBackground(new java.awt.Color(255, 255, 255));

        XemTKBLop.setBackground(new java.awt.Color(0, 204, 204));

        XemTKBLopLabel.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        XemTKBLopLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        XemTKBLopLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swing/images/class.png"))); // NOI18N
        XemTKBLopLabel.setText("Xem TKB lớp học");

        javax.swing.GroupLayout XemTKBLopLayout = new javax.swing.GroupLayout(XemTKBLop);
        XemTKBLop.setLayout(XemTKBLopLayout);
        XemTKBLopLayout.setHorizontalGroup(
            XemTKBLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(XemTKBLopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(XemTKBLopLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addContainerGap())
        );
        XemTKBLopLayout.setVerticalGroup(
            XemTKBLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(XemTKBLopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(XemTKBLopLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );

        AddTKB.setBackground(new java.awt.Color(0, 204, 204));

        AddTKBLabel.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        AddTKBLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddTKBLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swing/images/addCourse.png"))); // NOI18N
        AddTKBLabel.setText("Thêm TKB lớp học");

        javax.swing.GroupLayout AddTKBLayout = new javax.swing.GroupLayout(AddTKB);
        AddTKB.setLayout(AddTKBLayout);
        AddTKBLayout.setHorizontalGroup(
            AddTKBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddTKBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddTKBLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addContainerGap())
        );
        AddTKBLayout.setVerticalGroup(
            AddTKBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddTKBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddTKBLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );

        XemTKBSinhVien.setBackground(new java.awt.Color(0, 204, 204));

        XemTKBSinhVienLabel.setFont(new java.awt.Font("Lucida Grande", 0, 15)); // NOI18N
        XemTKBSinhVienLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        XemTKBSinhVienLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/swing/images/scoreSV.png"))); // NOI18N
        XemTKBSinhVienLabel.setText("Xem TKB sinh viên");

        javax.swing.GroupLayout XemTKBSinhVienLayout = new javax.swing.GroupLayout(XemTKBSinhVien);
        XemTKBSinhVien.setLayout(XemTKBSinhVienLayout);
        XemTKBSinhVienLayout.setHorizontalGroup(
            XemTKBSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(XemTKBSinhVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(XemTKBSinhVienLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addContainerGap())
        );
        XemTKBSinhVienLayout.setVerticalGroup(
            XemTKBSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(XemTKBSinhVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(XemTKBSinhVienLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout TabQLTKBLayout = new javax.swing.GroupLayout(TabQLTKB);
        TabQLTKB.setLayout(TabQLTKBLayout);
        TabQLTKBLayout.setHorizontalGroup(
            TabQLTKBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TabQLTKBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AddTKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(XemTKBLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(XemTKBSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TabQLTKBLayout.setVerticalGroup(
            TabQLTKBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabQLTKBLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TabQLTKBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(XemTKBSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(XemTKBLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddTKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ContentQLTKB.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout ContentQLTKBLayout = new javax.swing.GroupLayout(ContentQLTKB);
        ContentQLTKB.setLayout(ContentQLTKBLayout);
        ContentQLTKBLayout.setHorizontalGroup(
            ContentQLTKBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ContentQLTKBLayout.setVerticalGroup(
            ContentQLTKBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 458, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabQLTKB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ContentQLTKB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(TabQLTKB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ContentQLTKB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddTKB;
    private javax.swing.JLabel AddTKBLabel;
    private javax.swing.JPanel ContentQLTKB;
    private javax.swing.JPanel TabQLTKB;
    private javax.swing.JPanel XemTKBLop;
    private javax.swing.JLabel XemTKBLopLabel;
    private javax.swing.JPanel XemTKBSinhVien;
    private javax.swing.JLabel XemTKBSinhVienLabel;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
