//Codigo hecho por Julian Mercado Narvaez y Juan Pablo Ramos
//Tuvimos problemas a la hora de hacer usar el modelo de la tabla para actualizar una linea de codigo
package com.mycompany.solid.cleancode;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import org.bson.Document;

public class Main extends javax.swing.JFrame {

    private MongoDatabase mongodb;
    private MongoCollection<Document> mongocollection;

    public MongoClient createConnection() {
        MongoClient mongo = null;
        String server = "localhost";
        Integer port = 27017;
        try {
            mongo = MongoClients.create(
                    MongoClientSettings.builder()
                            .applyToClusterSettings(builder
                                    -> builder.hosts(Arrays.asList(new ServerAddress(server, port))))
                            .build());
            mongodb = mongo.getDatabase("SolidCleanCode");
            mongocollection = mongodb.getCollection("Inventory");

        } catch (MongoException e) {
            JOptionPane.showMessageDialog(null, "Error in the Mongo Connetion, error: " + e.toString());
        }
        return mongo;
    }

    public List<Product> readDB() {

        List<Product> readList = new ArrayList();
        for (Document doc : mongocollection.find()) {

            Product p = new Product();
            p.setProductId(doc.getInteger("Product Id"));
            p.setName(doc.getString("Product Name"));
            p.setPrice(doc.getDouble("Product Price").floatValue());
            p.setStock(doc.getInteger("Stock Product"));

            readList.add(p);

        }
        return readList;
    }

    public void add_product(Product p) {

        Document doc = new Document();
        doc.put("Product Id", p.getProductId());
        doc.put("Product Name", p.getName());
        doc.put("Product Price", p.getPrice());
        doc.put("Stock Product", p.getStock());

        mongocollection.insertOne(doc);
    }

    public void update_db(Product p) {

        BasicDBObject updated_bd = new BasicDBObject();
        updated_bd.append("Product Name", p.getName());
        updated_bd.append("Product Price", p.getPrice());
        updated_bd.append("Stock Product", p.getStock());

        mongocollection.updateOne(new BasicDBObject().append("Product Id", p.getProductId()), new BasicDBObject().append("$set", updated_bd));

    }

    private final ModelProductsTable dtm;

    private int SelectedRow;

    List<Product> products = new ArrayList<>();
    TableRowSorter<ModelProductsTable> tableRowSorter = new TableRowSorter<>();

    public Main() {

        createConnection();

        initComponents();
        initObjects();

        dtm = (ModelProductsTable) tblProducts.getModel();



    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jPasswordField1 = new javax.swing.JPasswordField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        txtStock = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        Confirm1 = new javax.swing.JButton();
        Confirm2 = new javax.swing.JButton();
        Confirm3 = new javax.swing.JButton();
        txtProduct = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtProductS = new javax.swing.JTextField();
        btnAddNewp = new javax.swing.JButton();
        txtStockS = new javax.swing.JTextField();
        txtPriceS = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnSelectP = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPasswordField1.setText("jPasswordField1");

        jToggleButton1.setText("jToggleButton1");

        jLabel13.setText("jLabel13");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setForeground(new java.awt.Color(204, 204, 204));
        setResizable(false);

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblProducts.setFocusable(false);
        tblProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProducts);

        btnAdd.setBackground(new java.awt.Color(0, 153, 204));
        btnAdd.setText("ADD PRODUCT");
        btnAdd.setEnabled(false);
        btnAdd.setOpaque(true);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(0, 153, 204));
        btnDelete.setText("REMOVE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(0, 153, 204));
        btnSearch.setText("SEARCH");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        txtStock.setEnabled(false);
        txtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockKeyTyped(evt);
            }
        });

        txtPrice.setEnabled(false);
        txtPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPriceKeyTyped(evt);
            }
        });

        Confirm1.setBackground(new java.awt.Color(0, 153, 204));
        Confirm1.setText("CONFIRM");
        Confirm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Confirm1ActionPerformed(evt);
            }
        });

        Confirm2.setBackground(new java.awt.Color(0, 153, 204));
        Confirm2.setText("CONFIRM");
        Confirm2.setEnabled(false);
        Confirm2.setOpaque(true);
        Confirm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Confirm2ActionPerformed(evt);
            }
        });

        Confirm3.setBackground(new java.awt.Color(0, 153, 204));
        Confirm3.setText("CONFIRM");
        Confirm3.setEnabled(false);
        Confirm3.setOpaque(true);
        Confirm3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Confirm3ActionPerformed(evt);
            }
        });

        txtProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductActionPerformed(evt);
            }
        });
        txtProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductKeyReleased(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel2.setText("PRODUCT INVENTORY");
        jLabel2.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel3.setText("ADD PRODUCT");
        jLabel3.setOpaque(true);

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("PRODUCT NAME:");

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setText("STOCK:");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setText("PRICE:");

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel7.setText("UPDATE PRODUCT");
        jLabel7.setOpaque(true);

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setText("PRICE:");

        txtProductS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductSActionPerformed(evt);
            }
        });

        btnAddNewp.setBackground(new java.awt.Color(0, 153, 204));
        btnAddNewp.setText("UPDATE PRODUCT");
        btnAddNewp.setEnabled(false);
        btnAddNewp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewpActionPerformed(evt);
            }
        });

        txtStockS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockSActionPerformed(evt);
            }
        });
        txtStockS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockSKeyTyped(evt);
            }
        });

        txtPriceS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceSActionPerformed(evt);
            }
        });
        txtPriceS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPriceSKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setText("PRODUCT NAME");

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel10.setText("STOCK:");

        jLabel11.setBackground(new java.awt.Color(204, 204, 204));
        jLabel11.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel11.setText("FILTER BY NAME");
        jLabel11.setOpaque(true);

        jLabel12.setBackground(new java.awt.Color(204, 204, 204));
        jLabel12.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel12.setText("REMOVE SELECTED PRODUCT");
        jLabel12.setOpaque(true);

        btnSelectP.setBackground(new java.awt.Color(0, 153, 204));
        btnSelectP.setText("SELECT PRODUCT");
        btnSelectP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 19, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(328, 328, 328)
                                        .addComponent(jLabel1)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(28, 28, 28))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel6))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(txtPrice)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(Confirm3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(txtStock)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(Confirm2))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(txtProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(Confirm1))))
                                        .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(btnSelectP)
                                        .addGap(39, 39, 39)
                                        .addComponent(btnAddNewp))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel9)
                                            .addComponent(txtProductS, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(txtStockS, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(txtPriceS, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 18, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(jLabel7))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(jLabel3)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Confirm1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(Confirm2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Confirm3)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProductS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStockS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPriceS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSelectP)
                            .addComponent(btnAddNewp)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete)
                            .addComponent(jLabel12))))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Product p = new Product();
        ModelProductsTable productModel = (ModelProductsTable) this.tblProducts.getModel();
        if (products.isEmpty()) {
            p.setProductId(1);
        } else {
            p.setProductId((int) (products.getLast().getProductId()) + 1);
        }

        p.setName(txtProduct.getText());
        p.setStock(Integer.valueOf(txtStock.getText()));
        p.setPrice(Float.parseFloat(txtPrice.getText()));

        add_product(p);

        productModel.AddProduct(p);
        
        txtProduct.setText("");
        txtPrice.setText("");
        txtStock.setText("");
        txtProduct.setEnabled(true);
        Confirm1.setEnabled(true);

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        ModelProductsTable productModel = (ModelProductsTable) this.tblProducts.getModel();
        mongocollection.deleteOne(new BasicDBObject().append("Product Id", tblProducts.getModel().getValueAt(tblProducts.getSelectedRow(), 0)));
        productModel.fireTableDataChanged();
        initObjects();


    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        RowFilter<ModelProductsTable, Integer> rowFilter = RowFilter.regexFilter(txtSearch.getText(), 1);
        tableRowSorter.setRowFilter(rowFilter);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void Confirm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Confirm1ActionPerformed
        
        if(txtProduct.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "It is empty, please enter a name");
        }else{
        txtProduct.setEnabled(false);
        Confirm1.setEnabled(false);
        txtStock.setEnabled(true);
        Confirm2.setEnabled(true);
        }        
    }//GEN-LAST:event_Confirm1ActionPerformed

    private void Confirm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Confirm2ActionPerformed
        if(txtStock.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "It is empty, please enter a value");
        }else{
        txtStock.setEnabled(false);
        Confirm2.setEnabled(false);
        txtPrice.setEnabled(true);
        Confirm3.setEnabled(true);
        }
    }//GEN-LAST:event_Confirm2ActionPerformed

    private void Confirm3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Confirm3ActionPerformed
        if(txtPrice.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "It is empty, please enter a value");
        }else{
        txtPrice.setEnabled(false);
        Confirm3.setEnabled(false);
        btnAdd.setEnabled(true);
        
        }
    }//GEN-LAST:event_Confirm3ActionPerformed

    private void txtProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductActionPerformed

    }//GEN-LAST:event_txtProductActionPerformed

    private void txtProductSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductSActionPerformed

    }//GEN-LAST:event_txtProductSActionPerformed

    private void btnAddNewpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewpActionPerformed
        if (txtProductS.getText().isBlank() || txtStockS.getText().isBlank() || txtPriceS.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Please update the data correctly");
        } else {

            int idp = (int) tblProducts.getValueAt(tblProducts.getSelectedRow(), 0);
            for (Product p : products) {
                if (p.getProductId() == idp) {
                    p.setName(txtProductS.getText());
                    p.setPrice(Float.parseFloat(txtPriceS.getText()));
                    p.setStock(Integer.parseInt(txtStockS.getText()));
                    update_db(p);
                    break;
                }
            }
            initObjects();

        }
    }//GEN-LAST:event_btnAddNewpActionPerformed

    private void txtStockSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockSActionPerformed

    private void btnSelectPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectPActionPerformed
      
        txtProductS.setText(dtm.getValueAt(tblProducts.getSelectedRow(), 1).toString());
        txtStockS.setText(dtm.getValueAt(tblProducts.getSelectedRow(), 2).toString());
        txtPriceS.setText(dtm.getValueAt(tblProducts.getSelectedRow(), 3).toString());

        btnAddNewp.setEnabled(true);
        btnSelectP.setEnabled(false);

        SelectedRow = tblProducts.getSelectedRow();
        
        

    }//GEN-LAST:event_btnSelectPActionPerformed

    private void tblProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductsMouseClicked
        tblProducts.isCellEditable(0, 0);
    }//GEN-LAST:event_tblProductsMouseClicked

    private void txtProductKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductKeyReleased
       
    }//GEN-LAST:event_txtProductKeyReleased

    private void txtStockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyTyped
        if (txtStock.getText().length() > 5){
            evt.consume();
        }
        int key = evt.getKeyChar();
        
        boolean numeros = key >= 48 && key <= 57;
        if (!numeros){
            evt.consume(); 
        }
        
    }//GEN-LAST:event_txtStockKeyTyped

    private void txtPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceKeyTyped
        if (txtPrice.getText().length() > 7){
            evt.consume();
        }
        int key = evt.getKeyChar();
        
        boolean numeros = key >= 48 && key <= 57;
        if (!numeros){
            evt.consume(); 
        }
    }//GEN-LAST:event_txtPriceKeyTyped

    private void txtStockSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockSKeyTyped
        if (txtStockS.getText().length() > 5){
            evt.consume();
        }
        int key = evt.getKeyChar();
        
        boolean numeros = key >= 48 && key <= 57;
        if (!numeros){
            evt.consume(); 
        }
        
    }//GEN-LAST:event_txtStockSKeyTyped

    private void txtPriceSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceSKeyTyped
        if (txtPriceS.getText().length() > 7){
            evt.consume();
        }
        int key = evt.getKeyChar();
        
        boolean numeros = key >= 48 && key <= 57;
        if (!numeros){
            evt.consume(); 
        }
        
    }//GEN-LAST:event_txtPriceSKeyTyped

    private void txtPriceSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceSActionPerformed
    }//GEN-LAST:event_txtPriceSActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    private void initObjects() {

        products = readDB();

        ModelProductsTable model = new ModelProductsTable(this.products);
        tableRowSorter = new TableRowSorter<>(model);
        tblProducts.setRowSorter(tableRowSorter);
        tblProducts.setModel(model);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Confirm1;
    private javax.swing.JButton Confirm2;
    private javax.swing.JButton Confirm3;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddNewp;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSelectP;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtPriceS;
    private javax.swing.JTextField txtProduct;
    private javax.swing.JTextField txtProductS;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtStockS;
    // End of variables declaration//GEN-END:variables

}
