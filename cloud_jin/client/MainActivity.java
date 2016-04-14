package cloud.client;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;

public class MainActivity extends Base {

    /* add more Request ID here 
	   ...

    */

    // views
    private JPanel main;
    private JLabel log;

    private JButton upload,logout;
    private GridLayout gl;

    // init the view layout here
/*
    private void setLayout(){
		
		gl = new GridLayout(10, 0);
		gl.setVgap(6);
		
		setTitle("Main");
        
		main = new JPanel();
        log = new JLabel("");
        upload = new JButton("upload");
        logout = new JButton("logout");

        JPanel treepanel = new Tree();
        treepanel.setBorder(Config.BLACKBORDER);
        main.add(treepanel, BorderLayout.CENTER);
        
//        log.setBorder(Config.REDBORDER);
//        upload.setBorder(Config.BLACKBORDER);
//        logout.setBorder(Config.BLACKBORDER);
        main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel buttonpanel = new JPanel(new GridLayout(1,3));
        buttonpanel.add(upload);
        buttonpanel.add(logout);
        buttonpanel.add(log);
        buttonpanel.setBorder(Config.BLACKBORDER);
        main.add(buttonpanel);
//      main.add(Box.createRigidArea(new Dimension(1,0)));
        
        setContentPane(main);
        pack();
        setSize((int)(Config.SCREENDIM.width*0.7),(int)(Config.SCREENDIM.height*0.7));
        setLocation(Config.SCREENDIM.width/2-getSize().width/2, Config.SCREENDIM.height/2-getSize().height/2);
        setVisible(true);
    }
*/
    private void setLayout(){	
		setTitle("Main Program");
        
		main = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		log = new JLabel("");
        upload = new JButton("upload");
        logout = new JButton("logout");

        JPanel treepanel = new Tree();
        c.insets = new Insets(0, 0, 0, 0);
//        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
//        treepanel.setBorder(Config.BLACKBORDER);        
        main.add(treepanel, c);
        
        JPanel buttonpanel = new JPanel(new GridLayout(1,4));
        buttonpanel.add(Box.createRigidArea(new Dimension(1,0)));
        buttonpanel.add(Box.createRigidArea(new Dimension(1,0)));

        buttonpanel.add(upload);
        buttonpanel.add(logout);
 //       buttonpanel.setBorder(Config.BLACKBORDER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 1;
        main.add(buttonpanel,c);

        JPanel logpanel = new JPanel();
        logpanel.add(log);
        c.gridx = 0;
        c.gridy = 2;
        main.add(logpanel,c);

        
//      main.add(Box.createRigidArea(new Dimension(1,0)));

        

        setContentPane(main);
        pack();
        setSize((int)(Config.SCREENDIM.width*0.7),(int)(Config.SCREENDIM.height*0.7));
        setLocation(Config.SCREENDIM.width/2-getSize().width/2, Config.SCREENDIM.height/2-getSize().height/2);
        setVisible(true);	
    	
    }
    
    // register event lisener
    private void registerLisener(){
    	addWindowListener(this);
    	upload.addActionListener(this);
        logout.addActionListener(this);
    }

	// initialize the layout
	public void start(){
		setLayout();
		registerLisener();
	}

	/*
	 * click event here
	 */
	public void actionPerformed(ActionEvent e) {
        log.setText(e.getActionCommand());
        if(e.getActionCommand() == "upload") {
        	log.setText("upload btn");

        }else if (e.getActionCommand() == "logout"){
        	log.setText("logout btn");

        }
    }

	// receive data, still process in background thread, heavy work here
	public HashMap<String,String> preReceive(int requestID,int tag,HashMap<String,String> data){
		if(requestID == Connection.GET_PATH){
			return null;
		}
		return data;
	}

	// receive data which need to display, in UI thread
	public void receive(int requestID,int tag,HashMap<String,String> data){

		if(data != null){
            System.out.println("ID:"+requestID+" Tag:"+tag+" Return Code:"+data.get("Status"));
		}
        if(tag == 106){
            //moveTo(LoginActivity.class);
        }
	}

    // test
    private void test() {
        FileManager fm = new FileManager();
        fm.mk("wo/");
        fm.mk("wo/data.txt");

        Connection cnn = new Connection();
        cnn.setRequestMethod(Connection.UPLOAD_FILE,"PUT");
        cnn.setRequestProperty("Auth",Auth.getInstance().toString());
        cnn.setRequestProperty("Connection","close");
        cnn.setRequestProperty("Path","wo/data.txt");
        request(cnn);
    }
}