package cloud.client;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.CompoundBorder;

public class LoginActivity extends Base {

    /* add more Request ID here 
	   ...

    */

    // views
    private JPanel main;
    private JLabel log, pw, id;
    private JTextField username;
    private JPasswordField password;

    private JButton loginbtn,registerbtn;
    private GridLayout gl, gl2;

    // drop-down select server
    public class ComboBoxes extends JPanel{ 
       public ComboBoxes(){
    	     super(new GridLayout(1,2));  	     
    	        String[] serverStrings = { "Server 1", "Server 2"};
    	        JLabel serverlabel = new JLabel("Server Name", JLabel.CENTER);
    	        JComboBox serverList = new JComboBox(serverStrings);
    	        serverList.setSelectedIndex(1);
    	        add(serverlabel);
    	        add(serverList);
       }
    }
    // End drop-list
    
    
    // init the view layout here
    private void setLayout(){    	
		gl = new GridLayout(5, 0);
		gl.setVgap(3);		
		gl2 = new GridLayout(0,2);
		gl2.setHgap(20);
		setTitle(" Cloud Login");
		main = new JPanel(gl);
        JPanel boxpanel = new JPanel();
        JPanel cbbox = new ComboBoxes();
        boxpanel.add(cbbox);
        main.add(boxpanel);

        JPanel idpanel = new JPanel(gl2);
        id = new JLabel("ID", JLabel.CENTER);
        username = new JTextField("",JTextField.CENTER);
        idpanel.add(id);
        idpanel.add(username);
        idpanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        main.add(idpanel);    
        
        JPanel pwpanel = new JPanel(gl2);
        JLabel pwlabel = new JLabel("Password", JLabel.CENTER);
        password = new JPasswordField("",JPasswordField.CENTER);

        pwpanel.add(pwlabel);
        pwpanel.add(password);
        pwpanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        main.add(pwpanel);
        
        loginbtn = new JButton("login");
        registerbtn = new JButton("register");
        JPanel button = new JPanel(gl2);
        button.add(loginbtn);
        button.add(registerbtn);
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        main.add(button);
        main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));              

        JPanel logpanel = new JPanel(new GridLayout(1,1));
        log = new JLabel("log label");
        logpanel.add(log);
        logpanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));              

        main.add(logpanel);        

        setContentPane(main);
        pack();
        setSize(300,300);
        setLocation(Config.SCREENDIM.width/2-getSize().width/2, Config.SCREENDIM.height/2-getSize().height/2);
        setVisible(true);
     
    }

    // register event lisener
    private void registerLisener(){
    	addWindowListener(this);
    	registerbtn.addActionListener(this);
        loginbtn.addActionListener(this);
    }

	// initialize the layout
	public void start(){
		setLayout();
		registerLisener();

        //test();
	}

	/*
	 * click event here
	 */
	public void actionPerformed(ActionEvent e) {
        log.setText(e.getActionCommand());
        if(e.getActionCommand() == "login") {
/*
        	Auth auth = Auth.getInstance();
        	Connection cnn = new Connection();
        	// get username and password and check. 
            // username:admin password:password
        	auth.setAccount(username.getText(),password.getText());
        	cnn.setRequestProperty("Auth",auth.toString());
			cnn.setRequestMethod(Connection.LOGIN,"POST");
            cnn.setTag(106);
			request(cnn);
			*/
        		moveTo(MainActivity.class);
        }

        else if(e.getActionCommand() == "register") {
        		moveTo(RegisterActivity.class);
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
			log.setText("ID:"+requestID+" Tag:"+tag+" Return Code:"+data.get("Status"));
            System.out.println("ID:"+requestID+" Tag:"+tag+" Return Code:"+data.get("Status"));

            if(tag == 106 && data.get("Status").equals(Connection.OK)){
                moveTo(MainActivity.class);
            }
		}
        
	}

    // test
    private void test(){
    }
}