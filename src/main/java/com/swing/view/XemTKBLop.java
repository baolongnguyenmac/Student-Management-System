package com.swing.view;

import javax.swing.JOptionPane;
import javax.swing.event.*;

import com.swing.controller.QLTKBController;

import java.awt.event.*;
import java.sql.SQLException;

public class XemTKBLop extends javax.swing.JPanel {

    public XemTKBLop() {
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
                        QLTKBController controller = new QLTKBController(ContentXemTKBLop);
                        controller.XemTKBLop(tenLop);
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
        jLabel1 = new javax.swing.JLabel();
        TenLopTextField = new javax.swing.JTextField();
        SubmitButton = new javax.swing.JButton();
        ContentXemTKBLop = new javax.swing.JPanel();

        jLabel1.setText("Nhập tên lớp: ");

        SubmitButton.setText("Xem TKB");

        javax.swing.GroupLayout ContentXemTKBLopLayout = new javax.swing.GroupLayout(ContentXemTKBLop);
        ContentXemTKBLop.setLayout(ContentXemTKBLopLayout);
        ContentXemTKBLopLayout.setHorizontalGroup(
            ContentXemTKBLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ContentXemTKBLopLayout.setVerticalGroup(
            ContentXemTKBLopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 367, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TenLopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SubmitButton)
                .addContainerGap(113, Short.MAX_VALUE))
            .addComponent(ContentXemTKBLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TenLopTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SubmitButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ContentXemTKBLop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JPanel ContentXemTKBLop;
    private javax.swing.JButton SubmitButton;
    private javax.swing.JTextField TenLopTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
