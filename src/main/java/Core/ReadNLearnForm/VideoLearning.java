/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Core.ReadNLearnForm;

import Core.ReadNLearn;

/**
 *
 * @author Marc
 */
public class VideoLearning extends javax.swing.JFrame {

	/**
	 * Creates new form VideoLearning
	 */
	public VideoLearning() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                letterSoundTabbedPane = new javax.swing.JTabbedPane();
                jScrollPane1 = new javax.swing.JScrollPane();
                vowels = new Core.ReadNLearnForm.VLessonsModules.Vowels();
                jScrollPane2 = new javax.swing.JScrollPane();
                consonants = new Core.ReadNLearnForm.VLessonsModules.Consonants();
                returnButton = new javax.swing.JButton();
                nextButton = new javax.swing.JButton();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                jScrollPane1.setViewportView(vowels);

                letterSoundTabbedPane.addTab("Lesson 1: Vowels", jScrollPane1);

                jScrollPane2.setViewportView(consonants);

                letterSoundTabbedPane.addTab("Lesson 2: Consonants", jScrollPane2);

                returnButton.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
                returnButton.setText("Return");
                returnButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                returnButtonMouseClicked(evt);
                        }
                });

                nextButton.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
                nextButton.setText("Next");
                nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                nextButtonMouseClicked(evt);
                        }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(letterSoundTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(letterSoundTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(193, Short.MAX_VALUE))
                );

                pack();
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

        private void nextButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextButtonMouseClicked
                // TODO add your handling code here:
            int selectedIndex = letterSoundTabbedPane.getSelectedIndex();
            if (selectedIndex < letterSoundTabbedPane.getTabCount() - 1) {
                letterSoundTabbedPane.setSelectedIndex(selectedIndex + 1);
            } else {
                letterSoundTabbedPane.setSelectedIndex(0);
            }
        }//GEN-LAST:event_nextButtonMouseClicked

        private void returnButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnButtonMouseClicked
                // TODO add your handling code here:
            int selectedIndex = letterSoundTabbedPane.getSelectedIndex();
            if (selectedIndex > 0) {
                letterSoundTabbedPane.setSelectedIndex(selectedIndex - 1);
            } else {
                VideoLearning.this.dispose();
                ReadNLearn  readNLearn = new ReadNLearn();
                readNLearn.setVisible(true);
            }
        }//GEN-LAST:event_returnButtonMouseClicked

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(VideoLearning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(VideoLearning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(VideoLearning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(VideoLearning.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new VideoLearning().setVisible(true);
			}
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private Core.ReadNLearnForm.VLessonsModules.Consonants consonants;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JTabbedPane letterSoundTabbedPane;
        private javax.swing.JButton nextButton;
        private javax.swing.JButton returnButton;
        private Core.ReadNLearnForm.VLessonsModules.Vowels vowels;
        // End of variables declaration//GEN-END:variables
}