/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.dposadsky.java.swingteacherdesktop.views;

import dev.dposadsky.java.swingteacherdesktop.controllers.MainFrameController;
import dev.dposadsky.java.swingteacherdesktop.controllers.PopupWindowsController;
import dev.dposadsky.java.swingteacherdesktop.main.Factory;
import dev.dposadsky.java.swingteacherdesktop.models.ComboBoxModel;
import dev.dposadsky.java.swingteacherdesktop.tables.CompletedTask;
import dev.dposadsky.java.swingteacherdesktop.tables.Lesson;
import dev.dposadsky.java.swingteacherdesktop.tables.Task;
import dev.dposadsky.java.swingteacherdesktop.tables.TaskCategory;
import dev.dposadsky.java.swingteacherdesktop.tables.User;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
/**
 *
 * @author DPosadsky
 */
public class MainFrameView extends JFrame {
    
    // Элементы интерфейса
    private JMenuBar menuBar; 
    private JLabel lessonLabel;
    private JLabel taskLabel;
    private JComboBox lessonComboBox;
    private JComboBox taskComboBox;
    private JButton lookButton;
    private JButton checkButton;
    private JScrollPane answerScrollPane;
    private JScrollPane questionScrollPane;
    private JScrollPane documentationScrollPane;
    
    // Вспомогательные компоненты
    private MainFrameController mainFrameController;
    private PopupWindowsController popupWindowsController;
    private User currentUser;

    // Данные из БД
    private ArrayList<Task> tasks;
    private ArrayList<Lesson> lessons;
    private ArrayList<CompletedTask> completedTasks;
    private ArrayList<Integer> completedTasksId;
    
    int taskCategory;
    int lesson;
    
    private TaskCategory categoryTask;

    public MainFrameView() {
        initComponents();
    }

    /*
    * Using in psvm
    */
    public MainFrameView(User user) {
        Factory factory = Factory.getInstance();
        factory.setCurrentUser(user);
        initComponents();
    }
    
    public void initComponents() {     
        Factory factory = Factory.getInstance();
        mainFrameController = factory.getMainFrameController();
        popupWindowsController = factory.getPopupWindowsController();
        currentUser = factory.getCurrentUser();    
  
        taskCategory = 1;
        lesson = 0;
        
        // Получение объектов из БД             
        lessons = mainFrameController.getLessonByCategory(taskCategory);
        tasks = mainFrameController.getTasksByLesson(lesson);
        completedTasks = mainFrameController.getCompletedTaskByUserId(currentUser.getId());
        
        completedTasksId = new ArrayList<>();
        for (CompletedTask completedTask : completedTasks) 
            completedTasksId.add(completedTask.getTaskId());
              
        // Инициализация компонентов 
        setLookAndFeel();
        menuBar = doCreateMenuBar();       
        lessonLabel = doCreateLabel("Урок");
        taskLabel = doCreateLabel("Задание");
        
        lessonLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        taskLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        
        lessonComboBox = new JComboBox(new ComboBoxModel(lessons));
        taskComboBox = new JComboBox(new ComboBoxModel(tasks));
        
        lessonComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        taskComboBox.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        //taskComboBox.setRenderer(new ComplexCellRenderer());
        
        //if (currentUser.getCompleteTraining() == 0) {
            //System.out.println(taskComboBox.getEditor().getEditorComponent().getName());
        //}
        
        for (int i = 0; i < tasks.size(); ++i) {
            Task cTask = (Task) taskComboBox.getItemAt(i);
            if (completedTasksId.contains( cTask.getId() ) )  {
                //taskComboBox.setSelectedItem(cTask);
                cTask.setTitle("☑ " + cTask.getTitle());
            }
        }
        
        if (!tasks.isEmpty()) {
            Task cTask = tasks.get(0);
            questionScrollPane = doCreateQuestionScrollPane( cTask.getQuestion() );
            documentationScrollPane = doCreateDocumentationScrollPane( 
                    mainFrameController.getDocumentation( cTask.getIdDocumentation() ).getText() );
        }
        else {
            questionScrollPane = doCreateQuestionScrollPane("Вопрос отсутствует");
            documentationScrollPane = doCreateDocumentationScrollPane("Документация отсутствует");
        }
        
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
 
        lessonComboBox.addActionListener(new ActionListener() {      
            @Override
            public void actionPerformed(ActionEvent ae) {
                changeSelectedElementInLessonComboBox(ae);
            }
        });
        
        taskComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changeSelectedElementInTaskComboBox(ae);
            }
        });
        
        lookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clickLookButton(ae);
            }
        });
        
        checkButton.addActionListener(new ActionListener() {           
            @Override
            public void actionPerformed(ActionEvent ae) { 
                clickOkButton(ae);
            }
        });
        
        // Настройка компонентов
        doSetupFrame();
        
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
    
    public void setLookAndFeel() {
        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException 
                | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public JMenuBar doCreateMenuBar() {
        
        Font font = new Font("Verdana", Font.PLAIN, 11);
         
        JMenuBar menuBar = new JMenuBar();
         
        JMenu settingsMenu = new JMenu("Настройки");
        settingsMenu.setFont(font);
         
        JMenuItem newUser = new JMenuItem("Новый пользователь");
        newUser.setFont(font);
        settingsMenu.add(newUser);
 
        JMenuItem changeUser = new JMenuItem("Сменить пользователя");
        changeUser.setFont(font);
        settingsMenu.add(changeUser);
   
        settingsMenu.addSeparator();
         
        JMenuItem exitItem = new JMenuItem("Выход");
        exitItem.setFont(font);
        settingsMenu.add(exitItem);
         
        exitItem.addActionListener(new ActionListener() {           
            public void actionPerformed(ActionEvent e) {
                System.exit(0);             
            }           
        });
         
        menuBar.add(settingsMenu);
        
        JMenu refMenu = new JMenu("Справка");
        
        JMenuItem documentationItem = new JMenuItem("Документация");
        documentationItem.setFont(font);
        refMenu.add(documentationItem);
        
        JMenuItem aboutProgram = new JMenuItem("О программе");
        aboutProgram.setFont(font);
        refMenu.add(aboutProgram);
        
        menuBar.add(refMenu);
        
        return menuBar;
        
    }
    
    public JLabel doCreateLabel(String text) {
        JLabel label = new JLabel(text);
        
        return label;
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
        
        CompletionProvider provider = mainFrameController.createCompletionProvider();
        AutoCompletion ac = new AutoCompletion(provider);
        ac.install(textArea);

        JScrollPane scrollPane = new JScrollPane(textArea);
        return scrollPane;
    } 
    
    public void changeSelectedElementInLessonComboBox(ActionEvent ae) {
        tasks = mainFrameController.getTasksByLesson(lessons.get(lessonComboBox.getSelectedIndex()).getId());
        taskComboBox.removeAllItems();
        JEditorPane pane = ((JEditorPane)documentationScrollPane.getViewport().getView());
        Task cTask = null;
        pane.setText("Справочная информация по данному заданию отсутствует");
        if (!tasks.isEmpty()) {
            cTask = tasks.get(0);
            ((JEditorPane)questionScrollPane.getViewport().getView()).setText(cTask.getQuestion());
            pane.setText(mainFrameController.getDocumentation(cTask.getIdDocumentation()).getText());
            for (Task task: tasks)
                taskComboBox.addItem(task);
            for (int i = 0; i < tasks.size(); ++i)  {
                cTask = (Task) taskComboBox.getItemAt(i) ;
                if (completedTasksId.contains(cTask.getId() ) ) 
                    cTask.setTitle("☑ " + cTask.getTitle());    
            }
        }
    }
    
    public void changeSelectedElementInTaskComboBox(ActionEvent ae) {
        JEditorPane pane = (JEditorPane) questionScrollPane.getViewport().getView();
        JEditorPane paneDoc = (JEditorPane) documentationScrollPane.getViewport().getView();
        if (taskComboBox.getItemCount() != 0) {
            Task cTask = (Task) taskComboBox.getSelectedItem();
            pane.setText(cTask.getQuestion());
            paneDoc.setText(mainFrameController.getDocumentation(cTask.getIdDocumentation()).getText() );
        }
        else {
            pane.setText("Вопрос отсутствует");
            paneDoc.setText( "Документация отсутствует" );
        }
    }
    
    public void clickLookButton(ActionEvent ae) {
        String imports = "";
        Object o = taskComboBox.getSelectedItem();
        if (o != null) 
            imports = ( (Task) o ).getImports();
        mainFrameController.loadAndRunClassFromFile( 
                ( (JTextArea)answerScrollPane.getViewport().getView() ).getText(), imports);
    }
    
    public void clickOkButton(ActionEvent ae) {
        String errors = null;
        if (taskComboBox.getItemCount() == 0) {
            popupWindowsController.createPopupWindow("Сначала выберите вопрос!", "Ошибка!");
            return;
        }
        Task cTask = (Task) taskComboBox.getSelectedItem();
        if (mainFrameController.getCompletedTaskByUserIdByTaskId(currentUser.getId(),cTask.getId()) != null) {
            popupWindowsController.createPopupWindow("Задание уже выполнено!", "Ошибка!");
            return;
        }
            
        /*
        if (cTask.getAnswer() == null) {
            popupWindowsController.createPopupWindow("На данное задание отсутствует ответ", "Ошибка!");
            return;
        }
        */
        JTextArea cTextArea = (JTextArea) answerScrollPane.getViewport().getView();
        errors = mainFrameController.isFileCompile( cTextArea.getText(), cTask.getImports() );
        if (!errors.isEmpty()) {
            popupWindowsController.createPopupWindow(errors, "Ошибка компиляции!");
            return;
        }
        errors = mainFrameController.check(cTask.getAnswer(), cTextArea.getText());
        if (!errors.isEmpty()) {
            popupWindowsController.createPopupWindow(errors, "Ошибка выполнения задания!");
            return;
        }
        
        CompletedTask completedTask = new CompletedTask();
        completedTask.setTaskId( cTask.getId() );
        completedTask.setUserId(currentUser.getId());
        mainFrameController.addCompletedTask(completedTask);
        completedTasksId.add(cTask.getId());
        cTask.setTitle("☑ " + cTask.getTitle());
        ( (JTextArea)answerScrollPane.getViewport().getView() ).setText("");
        popupWindowsController.createPopupWindow(new JFrame(), "Решение верное!", "Ok");
        if (taskComboBox.getItemCount() > taskComboBox.getSelectedIndex() + 1)
            taskComboBox.setSelectedIndex(taskComboBox.getSelectedIndex() + 1);
    }

    public void setUser(User user) {
        this.currentUser = user;
    }

    public JComboBox getTaskComboBox() {
        return taskComboBox;
    }

    public ArrayList<Integer> getCompletedTasksId() {
        return completedTasksId;
    }
}
