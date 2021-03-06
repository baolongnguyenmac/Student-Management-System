package com.swing.view;

import javax.swing.JOptionPane;
import javax.swing.event.*;

import com.swing.controller.QLLopHocController;

import java.awt.event.*;
import java.sql.SQLException;

public class XemDSLop extends javax.swing.JPanel {

    public XemDSLop() {
        initComponents();

        setEvent();
    }

    public void setEvent() {
        SubmitButton.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (!isNotNull()) {
                        showDialogAgain("Vui lòng nhập tên lớp!");
                    }
                    else {
                        String tenLop = TenLopTextField.getText();
                        QLLopHocController controller = new QLLopHocController(ContentXemDSLop);
                        controller.XemDSLop(tenLop);
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
        return TenLopTextField.getText() != null && !TenLopTextField.getText().equalsIgnoreCase("");
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
        TenLopTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        ContentXemDSLop = new javax.swing.JPanel();
        SubmitButton = new javax.swing.JButton();

        TenLopTextField.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel1.setText("Nhập tên lớp: ");

        javax.swing.GroupLayout ContentXemDSLopLayout = new javax.swing.GroupLayout(ContentXemDSLop);
        ContentXemDSLop.setLayout(ContentXemDSLopLayout);
        ContentXemDSLopLayout.setHorizontalGroup(
            ContentXemDSLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ContentXemDSLopLayout.setVerticalGroup(
            ContentXemDSLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 503, Short.MAX_VALUE)
        );

        SubmitButton.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        SubmitButton.setText("Xuất danh sách");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TenLopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SubmitButton)
                .addContainerGap(86, Short.MAX_VALUE))
            .addComponent(ContentXemDSLop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TenLopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(SubmitButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ContentXemDSLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JPanel ContentXemDSLop;
    private javax.swing.JButton SubmitButton;
    private javax.swing.JTextField TenLopTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
