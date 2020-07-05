package com.swing.view;

import javax.swing.JOptionPane;
import javax.swing.event.*;

import com.swing.controller.ThongKeController;

import java.awt.event.*;
import java.sql.SQLException;

public class ThongKeDuLieu extends javax.swing.JPanel {

    public ThongKeDuLieu() {
        initComponents();

        setEvent();
    }

    // set event cho cái button để bắt đầu làm thống kê 
    public void setEvent() {
        SubmitButton.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (!isNotNull()) {
                        showDialogAgain("Vui lòng nhập đủ các trường dữ liệu!");
                    }
                    else {
                        String tenLop = TenLopTextField.getText();
                        String tenMonHoc = TenMonHocTextField.getText();
                        ThongKeController controller = new ThongKeController(TableQuaMon, TableKhongQuaMon);
                        float quaMon = controller.ThongKeQuaMon(tenLop, tenMonHoc);
                        TyLeQuaMonLabel.setText(Float.toString(quaMon));
                        float khongQuaMon = controller.ThongKeKhongQuaMon(tenLop, tenMonHoc);
                        TyLeKhongQuaMonLabel.setText(Float.toString(khongQuaMon));
                    }
                }
                catch (SQLException se) {
                    showDialogAgain(se.getMessage());
                    System.out.println(se);
                }
                catch (Exception ex) {
                    showDialogAgain("Đã có lỗi xảy ra, hãy kiểm tra input");
                    System.err.println(ex);
                }
            }
        });
    }

    public boolean isNotNull() {
        return TenLopTextField.getText() != null && !TenLopTextField.getText().equalsIgnoreCase("")
            && TenMonHocTextField.getText() != null && !TenMonHocTextField.getText().equalsIgnoreCase("");
    }

    private void showDialogAgain(String str) {
        JOptionPane.showMessageDialog(null, str);
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
        jLabel1 = new javax.swing.JLabel();
        TenLopTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TenMonHocTextField = new javax.swing.JTextField();
        SubmitButton = new javax.swing.JButton();
        ContentQuaMon = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        TyLeQuaMonLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TableQuaMon = new javax.swing.JPanel();
        ContentKhongQuaMon = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TyLeKhongQuaMonLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TableKhongQuaMon = new javax.swing.JPanel();

        jLabel1.setText("Nhập tên lớp: ");

        jLabel2.setText("Nhập tên môn học: ");

        SubmitButton.setText("Phân tích");

        ContentQuaMon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dữ liệu sinh viên qua môn ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 13))); // NOI18N

        jLabel3.setText("Tỷ lệ qua môn: ");

        jLabel5.setText("Đơn vị: %");

        javax.swing.GroupLayout TableQuaMonLayout = new javax.swing.GroupLayout(TableQuaMon);
        TableQuaMon.setLayout(TableQuaMonLayout);
        TableQuaMonLayout.setHorizontalGroup(
            TableQuaMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        TableQuaMonLayout.setVerticalGroup(
            TableQuaMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 193, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ContentQuaMonLayout = new javax.swing.GroupLayout(ContentQuaMon);
        ContentQuaMon.setLayout(ContentQuaMonLayout);
        ContentQuaMonLayout.setHorizontalGroup(
            ContentQuaMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContentQuaMonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TyLeQuaMonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(TableQuaMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ContentQuaMonLayout.setVerticalGroup(
            ContentQuaMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContentQuaMonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ContentQuaMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TyLeQuaMonLabel)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TableQuaMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ContentKhongQuaMon.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dữ liệu sinh viên không qua môn ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 1, 13))); // NOI18N

        jLabel4.setText("Tỷ lệ không qua môn: ");

        jLabel7.setText("Đơn vị: %");

        javax.swing.GroupLayout TableKhongQuaMonLayout = new javax.swing.GroupLayout(TableKhongQuaMon);
        TableKhongQuaMon.setLayout(TableKhongQuaMonLayout);
        TableKhongQuaMonLayout.setHorizontalGroup(
            TableKhongQuaMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        TableKhongQuaMonLayout.setVerticalGroup(
            TableKhongQuaMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 182, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ContentKhongQuaMonLayout = new javax.swing.GroupLayout(ContentKhongQuaMon);
        ContentKhongQuaMon.setLayout(ContentKhongQuaMonLayout);
        ContentKhongQuaMonLayout.setHorizontalGroup(
            ContentKhongQuaMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContentKhongQuaMonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TyLeKhongQuaMonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(TableKhongQuaMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ContentKhongQuaMonLayout.setVerticalGroup(
            ContentKhongQuaMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContentKhongQuaMonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ContentKhongQuaMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TyLeKhongQuaMonLabel)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TableKhongQuaMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContentQuaMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TenMonHocTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TenLopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(SubmitButton)
                .addContainerGap(33, Short.MAX_VALUE))
            .addComponent(ContentKhongQuaMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TenLopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TenMonHocTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SubmitButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ContentQuaMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ContentKhongQuaMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JPanel ContentKhongQuaMon;
    private javax.swing.JPanel ContentQuaMon;
    private javax.swing.JButton SubmitButton;
    private javax.swing.JPanel TableKhongQuaMon;
    private javax.swing.JPanel TableQuaMon;
    private javax.swing.JTextField TenLopTextField;
    private javax.swing.JTextField TenMonHocTextField;
    private javax.swing.JLabel TyLeKhongQuaMonLabel;
    private javax.swing.JLabel TyLeQuaMonLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
