package app;

import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JTextField;
import logic.Cuboid;
import logic.Item;
import logic.Service;
import logic.Vehicle;

/**
 *
 * @author SHUBHAM PURANIK
 */

public class MainApp extends javax.swing.JFrame {
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Item> items;
    private static int orderID=1;
    private static int row=0;
    public MainApp() {
        initComponents();
        vehicles=Vehicle.getVehicles();
        for(Vehicle v:vehicles)
            jComboBoxSelectVehicle.addItem(v.name);
        items=Item.getItems();
        for(Item i:items)
            jComboBoxSelectItem.addItem(i.name);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBoxSelectVehicle = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxSelectItem = new javax.swing.JComboBox();
        jButtonAddItem = new javax.swing.JButton();
        jButtonNextOrder = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonPack = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldItemQuantity = new javax.swing.JTextField();
        jLabelItemName = new javax.swing.JLabel();
        jLabelItemQuantity = new javax.swing.JLabel();
        jLabelOrderID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("SELECT VEHICLE");

        jComboBoxSelectVehicle.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBoxSelectVehicle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT VEHICLE" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("SELECT ITEMS");

        jComboBoxSelectItem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECT ITEM" }));
        jComboBoxSelectItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSelectItemActionPerformed(evt);
            }
        });

        jButtonAddItem.setText("ADD ITEM");
        jButtonAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddItemActionPerformed(evt);
            }
        });

        jButtonNextOrder.setText("NEXT ORDER");
        jButtonNextOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextOrderActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Order ID", "Item Name", "Item Quantity"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButtonPack.setText("PACK");
        jButtonPack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPackActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("ENTER QUANTITY");

        jTextFieldItemQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldItemQuantityKeyReleased(evt);
            }
        });

        jLabelItemName.setText("ITEM NAME:- ");

        jLabelItemQuantity.setText("ITEM QUANTITY:- ");

        jLabelOrderID.setText("ORDER ID:- ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jButtonPack, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelOrderID, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBoxSelectItem, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxSelectVehicle, javax.swing.GroupLayout.Alignment.TRAILING, 0, 181, Short.MAX_VALUE)
                                    .addComponent(jLabelItemName))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldItemQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelItemQuantity)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                        .addComponent(jButtonAddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonNextOrder)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxSelectVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxSelectItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldItemQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddItem)
                    .addComponent(jButtonNextOrder)
                    .addComponent(jLabelItemName)
                    .addComponent(jLabelOrderID)
                    .addComponent(jLabelItemQuantity))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPack)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddItemActionPerformed
        jTable1.setValueAt(jLabelOrderID.getText().split(":- ")[1],row,0);
        jTable1.setValueAt(jLabelItemName.getText().split(":- ")[1],row,1);
        jTable1.setValueAt(jLabelItemQuantity.getText().split(":- ")[1],row++,2);
    }//GEN-LAST:event_jButtonAddItemActionPerformed

    private void jComboBoxSelectItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSelectItemActionPerformed
        jLabelItemName.setText("ITEM NAME:- ");
        jLabelItemName.setText(jLabelItemName.getText()+jComboBoxSelectItem.getSelectedItem());
    }//GEN-LAST:event_jComboBoxSelectItemActionPerformed

    private void jButtonNextOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextOrderActionPerformed
        // TODO add your handling code here:
        jLabelOrderID.setText("ORDER ID:- "+(orderID++));
    }//GEN-LAST:event_jButtonNextOrderActionPerformed

    private void jTextFieldItemQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldItemQuantityKeyReleased
        // TODO add your handling code here:
        JTextField jtf=(JTextField)evt.getSource();
//        JOptionPane.showMessageDialog(this,jtf.getText());
        String text=jtf.getText();
        Pattern pattern=Pattern.compile("[0-9]+");
        if(text.matches(pattern.pattern())){
            if(Integer.parseInt(text)>0){
                jLabelItemQuantity.setText("ITEM QUANTITY:- "+jTextFieldItemQuantity.getText());
            }else{
                jLabelItemQuantity.setText("ITEM QUANTITY:- ");
            }
        }else{
            jLabelItemQuantity.setText("ITEM QUANTITY:- ");
        }
    }//GEN-LAST:event_jTextFieldItemQuantityKeyReleased

    private void jButtonPackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPackActionPerformed
        // TODO add your handling code here:
        Vehicle vehicle=Vehicle.getVehicle(jComboBoxSelectVehicle.getSelectedItem().toString());
        ArrayList<Item> items=new ArrayList<>();
        for(int i=0;i<row;i++){
            long id=Long.parseLong(jTable1.getValueAt(i, 0).toString());
            String itemName=jTable1.getValueAt(i,1).toString();
            Item item=Item.getItem(itemName);
            item.id=id;
            for(int j=0;j<Integer.parseInt(jTable1.getValueAt(i,2).toString());j++){
                items.add(item);
                System.out.println(item);
            }
        }
        ArrayList<Cuboid> cuboids=Service.getCuboids(vehicle, items);
        Render3D.runThis(cuboids);
    }//GEN-LAST:event_jButtonPackActionPerformed

    public static void main(String args[]) {
        new MainApp().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddItem;
    private javax.swing.JButton jButtonNextOrder;
    private javax.swing.JButton jButtonPack;
    private javax.swing.JComboBox jComboBoxSelectItem;
    private javax.swing.JComboBox jComboBoxSelectVehicle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelItemName;
    private javax.swing.JLabel jLabelItemQuantity;
    private javax.swing.JLabel jLabelOrderID;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldItemQuantity;
    // End of variables declaration//GEN-END:variables
}
