/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.views;

import dev.dposadsky.java.swingteacherdesktop.controllers.MainFrameController;
import dev.dposadsky.java.swingteacherdesktop.models.ComboBoxModel;
import dev.dposadsky.java.swingteacherdesktop.tables.CompletedTask;
import dev.dposadsky.java.swingteacherdesktop.tables.Lesson;
import dev.dposadsky.java.swingteacherdesktop.tables.Task;
import dev.dposadsky.java.swingteacherdesktop.tables.TaskCategory;
import dev.dposadsky.java.swingteacherdesktop.tables.User;
import dev.dposadsky.java.swingteacherdesktop.utils.StringUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

/**
 *
 * @author DPosadsky
 */
public class MainFrameView extends JFrame {
    
    User currentUser;
    
    // Основные компоненты
    private JMenuBar menuBar;
    
    //
    private JLabel lessonLabel;
    private JLabel taskLabel;
    private JComboBox lessonComboBox;
    private JComboBox taskComboBox;
    private JButton lookButton;
    private JButton checkButton;
    private JScrollPane answerScrollPane;
    private JScrollPane questionScrollPane;
    private JScrollPane documentationScrollPane;
  
    // Разметки 
    BorderLayout mainBorderLayout;
    
    /* 
    ** Вспомогательные компаненты
    */
    
    private MainFrameController controller;

    // Данные из БД
    ArrayList<Task> tasks;
    ArrayList<Lesson> lessons;
    ArrayList<CompletedTask> completedTasks;
    ArrayList<Integer> completedTasksId;
    
    int taskCategory;
    int lesson;
    
    TaskCategory categoryTask;

    public MainFrameView() {
        initComponents();
    }
    
    public MainFrameView(User user) {
        this.currentUser = user;
        initComponents();
    }
    
    public void initComponents() {  
        
        controller = new MainFrameController();
        currentUser = controller.getCurrentUser();
        
        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException ex) {
            
        } 
  
        taskCategory = 1;
        lesson = 0;
        
        // Получение объектов из БД             
        lessons = controller.getLessonByCategory(taskCategory);
        tasks = controller.getTasksByLesson(lesson);
        completedTasks = controller.getCompletedTaskByUserId(currentUser.getId());
        completedTasksId = new ArrayList<Integer>();
        for (CompletedTask completedTask : completedTasks) {
            completedTasksId.add(completedTask.getTaskId());
        }
        
     
        // Инициализация компонентов 

        menuBar = doCreateMenuBar();
        
        lessonLabel = doCreateLabel("Урок");
        taskLabel = doCreateLabel("Задание");
        lessonComboBox = new JComboBox(new ComboBoxModel(lessons));
        taskComboBox = new JComboBox(new ComboBoxModel(tasks));
        System.out.println(completedTasksId.toString());
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println(( (Task) taskComboBox.getItemAt(i) ).getId() );
            if (completedTasksId.contains( ( (Task) taskComboBox.getItemAt(i) ).getId() ) ) {
                //System.out.println("Yes!!" + ( (Task) taskComboBox.getItemAt(i) ).getId() );
                //taskComboBox.getComponent(i).setBackground(Color.yellow);
                //taskComboBox.getComponents()[i].setBackground(Color.yellow);
                ( (Task) taskComboBox.getItemAt(i) ).setTitle("☑ " + ( (Task) taskComboBox.getItemAt(i) ).getTitle());
            }
        }
        //taskComboBox.getItemCount()
        questionScrollPane = doCreateQuestionScrollPane((!tasks.isEmpty()) 
                ? tasks.get(0).getQuestion() : "Вопрос");
        documentationScrollPane = doCreateDocumentationScrollPane((!tasks.isEmpty()) 
                ? controller.getDocumentation(tasks.get(0).getIdDocumentation()).getText() : "Вопрос");
        answerScrollPane = doCreateAnswerScrollPane("");
        lookButton = doCreateButton("Посмотреть");
        checkButton = doCreateButton("Проверить");
        
            
        // Связывание компонентов
        setJMenuBar(menuBar);
        
        MigLayout migLayout = new MigLayout("wrap 16", "grow, fill");
        setLayout(migLayout);
        
        add(lessonLabel, "span 2");
        add(lessonComboBox, "span 2, w 20%");
        add(taskLabel, "span 2");
        add(taskComboBox, "span 2, w 20%");
        add(documentationScrollPane, "span 8 8, w 50%, h 100%");
        add(questionScrollPane, "span 8 2, h 30%");
        add(answerScrollPane, "span 8 4, h 70%");
        add(lookButton, "span 4");
        add(checkButton, "span 4");

        
        // Добавление слушателей
        
        this.addComponentListener(new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent ce) {
                //questionScrollPane.setSize(Window.getFocusedWindow().getWidth()/2,questionScrollPane.getHeight());
            }

            @Override
            public void componentMoved(ComponentEvent ce) {
            }

            @Override
            public void componentShown(ComponentEvent ce) {
            }

            @Override
            public void componentHidden(ComponentEvent ce) {
            }
        });
        
        lessonComboBox.addActionListener(new ActionListener() {      
            @Override
            public void actionPerformed(ActionEvent ae) {
                addActionListenerToLessonComboBox(ae);
            }
        });
        
        taskComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                addActionListenerToTaskComboBox(ae);
            }
        });
        
        lookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                addActionListenerToLookButton(ae);
            }
        });
        
        checkButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) { 
                String errors = null;
                if (taskComboBox.getItemCount() != 0) {
                    Task cTask = (Task) taskComboBox.getSelectedItem();
                    if (cTask.getAnswer() != null) {
                        errors = controller.isFileCompile(((JTextArea)answerScrollPane.getViewport().getView()).getText(), cTask.getImports());
                        if (errors.isEmpty()) {
                            errors = controller.check(cTask.getAnswer(), ((JTextArea)answerScrollPane.getViewport().getView()).getText());
                            if (errors.isEmpty()) {
                                CompletedTask completedTask = new CompletedTask();
                                completedTask.setTaskId(((Task) taskComboBox.getSelectedItem()).getId());
                                completedTask.setUserId(currentUser.getId());
                                controller.addCompletedTask(completedTask);
                                ( (Task) taskComboBox.getSelectedItem() ).setTitle("☑ " + ( (Task) taskComboBox.getSelectedItem() ).getTitle());
                                JOptionPane.showMessageDialog(new JFrame(), "Решение верное!", "Ok", JOptionPane.DEFAULT_OPTION );
                                if (taskComboBox.getItemCount() > taskComboBox.getSelectedIndex() + 1)
                                taskComboBox.setSelectedIndex(taskComboBox.getSelectedIndex() + 1);
                            }
                            else
                                JOptionPane.showMessageDialog(new JFrame(), errors, "Ошибка!", JOptionPane.DEFAULT_OPTION );

                        }
                        else
                            JOptionPane.showMessageDialog(new JFrame(), errors, "Ошибка!", JOptionPane.DEFAULT_OPTION );
                    }
                    else
                        System.out.println("На данное задание отсутствует ответ");
                }
                else
                    System.out.println("Сначала выберите вопрос!");
            }
        });
        
        // Настройка компонентов
        doSetupFrame();
        
        
    }
    
    public JFrame getMainFrame() {
        return this;
    }
 
    public void doSetupFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setMinimumSize(new Dimension(1000, 600));
        //setPreferredSize(new Dimension(1000, 600));
        setVisible(true);
        //pack();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    public JMenuBar doCreateMenuBar() {
        
        Font font = new Font("Verdana", Font.PLAIN, 11);
         
        JMenuBar menuBar = new JMenuBar();
         
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(font);
         
        JMenu newMenu = new JMenu("New");
        newMenu.setFont(font);
        fileMenu.add(newMenu);
         
        JMenuItem exampleOneItem = new JMenuItem("Example 1");
        exampleOneItem.setFont(font);
        newMenu.add(exampleOneItem);
         
        JMenuItem exampleTwoItem = new JMenuItem("Example 2");
        exampleTwoItem.setFont(font);
        newMenu.add(exampleTwoItem);
         
        JMenuItem exampleThreeItem = new JMenuItem("Example 3");
        exampleThreeItem.setFont(font);
        newMenu.add(exampleThreeItem);
         
        JMenuItem openItem = new JMenuItem("Open");
        openItem.setFont(font);
        fileMenu.add(openItem);
         
        JMenuItem closeItem = new JMenuItem("Close");
        closeItem.setFont(font);
        fileMenu.add(closeItem);
         
        JMenuItem closeAllItem = new JMenuItem("Close all");
        closeAllItem.setFont(font);
        fileMenu.add(closeAllItem);
         
        fileMenu.addSeparator();
         
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(font);
        fileMenu.add(exitItem);
         
        exitItem.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
                System.exit(0);             
            }           
        });
         
        menuBar.add(fileMenu);
        
        return menuBar;
        
    }
    
    public JLabel doCreateLabel(String text) {
        JLabel label = new JLabel(text);
        
        return label;
    }
    
    public JComboBox doCreateComboBoxLessons(String[] text) {
        JComboBox comboBox = new JComboBox(text);
        
        return comboBox;
    }
   
    
    public JButton doCreateButton(String text) {
        JButton button = new JButton(text);
        
        return button;
    }
    
    public JScrollPane doCreateDocumentationScrollPane(String text) {
        JEditorPane html = new JEditorPane();
        html.setContentType("text/html;Content-Type=windows-1251");
        html.setEditable(false);
        html.setText(text);
        TitledBorder tBorder = BorderFactory.createTitledBorder("Справка");
        tBorder.setTitleFont(new Font("Serif", Font.BOLD, 15));
        html.setBorder(tBorder);

        JScrollPane scrollPane = new JScrollPane(html);
        return scrollPane;
    }
    
    public JScrollPane doCreateQuestionScrollPane(String text) {
        JEditorPane html = new JEditorPane();
        html.setContentType("text/html;Content-Type=windows-1251");
        html.setEditable(false);
        html.setText(text);
        TitledBorder tBorder = BorderFactory.createTitledBorder("Задание");
        tBorder.setTitleFont(new Font("Serif", Font.BOLD, 15));
        html.setBorder(tBorder);
        JScrollPane scrollPane = new JScrollPane(html);
        scrollPane.setMinimumSize(new Dimension(500, 60));
        return scrollPane;
    } 
     
    public JScrollPane doCreateAnswerScrollPane(String text) {
        RSyntaxTextArea textArea = new RSyntaxTextArea(text);

        TitledBorder tBorder = BorderFactory.createTitledBorder("Решение");
        tBorder.setTitleFont(new Font("Serif", Font.BOLD, 15));
        textArea.setBorder(tBorder);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA); 
        
        CompletionProvider provider = controller.createCompletionProvider();
        AutoCompletion ac = new AutoCompletion(provider);
        ac.install(textArea);

        JScrollPane scrollPane = new JScrollPane(textArea);
        return scrollPane;
    } 
    
    public void addActionListenerToLessonComboBox(ActionEvent ae) {
        tasks = controller.getTasksByLesson(lessons.get(lessonComboBox.getSelectedIndex()).getId());
        taskComboBox.removeAllItems();
        ((JEditorPane)documentationScrollPane.getViewport().getView()).setText("Справочная информация по данному заданию отсутствует");
        if (!tasks.isEmpty()) {
            ((JEditorPane)questionScrollPane.getViewport().getView()).setText(tasks.get(0).getQuestion());
            ((JEditorPane)documentationScrollPane.getViewport().getView()).setText(controller.getDocumentation(tasks.get(0).getIdDocumentation()).getText());
            for (Task task: tasks) {
                taskComboBox.addItem(task);
            }
            System.out.println(completedTasksId.toString());
            for (int i = 0; i < tasks.size(); ++i) {
                System.out.println(( (Task) taskComboBox.getItemAt(i) ).getId());
                if (completedTasksId.contains( ( (Task) taskComboBox.getItemAt(i) ).getId() ) ) {
                    ( (Task) taskComboBox.getItemAt(i) ).setTitle("☑ " + ( (Task) taskComboBox.getItemAt(i) ).getTitle());
                }
        }
        }
    }
    
    public void addActionListenerToTaskComboBox(ActionEvent ae) {
        if (taskComboBox.getItemCount() != 0) {
            Task cTask = (Task) taskComboBox.getSelectedItem();
            ((JEditorPane)questionScrollPane.getViewport().getView()).setText(cTask.getQuestion());
            ( (JEditorPane) documentationScrollPane.getViewport().getView() ).setText( controller.getDocumentation( cTask.getIdDocumentation() ).getText() );
        }
        else {
            ((JEditorPane)questionScrollPane.getViewport().getView()).setText("Вопрос отсутствует");
            ( (JEditorPane) documentationScrollPane.getViewport().getView() ).setText( "Документация отсутствует" );

        }
    }
    
    public void addActionListenerToLookButton(ActionEvent ae) {
        String imports = "";
        if (taskComboBox.getSelectedItem() != null) {
            Task cTask = (Task) taskComboBox.getSelectedItem();
            imports = cTask.getImports();
        }
        controller.loadAndRunClassFromFile(((JTextArea)answerScrollPane.getViewport().getView()).getText(), imports);
    }
    
    public void componentResized(ComponentEvent event) {
        System.out.println("111");
    }
    
    public void setUser(User user) {
        this.currentUser = user;
    }
    
    public static void main(String[] args) {
        User user = new User();
        user.setEmail("dmitry.posadsky@gmail.com");
        user.setLastLogin(1);
        user.setLogin("admin");
        user.setPassword(StringUtils.md5Apache("25531094"));
        user.setId(1);
        MainFrameView mfv = new MainFrameView(user);
        
    }
}
