import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotePad implements ActionListener {
    JFrame frame, jf;
    JMenuBar menubar;
    JMenu file, edit, format, help;
    JMenuItem neww, open, save, saveas, page_setup, print, exit, cut, copy, paste, replace, date_time, font, font_color, textarea_color;
    JTextArea textArea;
    String title = "Untitled_Notepad";
    File filee;
    JTextField jt1, jt2;
    JButton jb;

    NotePad() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame = new JFrame();
        frame.setTitle(title);
        frame.setSize(500, 500);
        frame.setLocation(500, 150);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        ImageIcon imageIcon = new ImageIcon("D:\\IntelliJ IDEA\\NotePad_Editor\\src\\notepad_icon.png");
        frame.setIconImage(imageIcon.getImage());

        frame.setJMenuBar(MenuItem());

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    public JMenuBar MenuItem() {
        menubar = new JMenuBar();
        //File
        file = new JMenu("File");

        neww = new JMenuItem("New");
        neww.addActionListener(this);
        file.add(neww);

        open = new JMenuItem("Open");
        open.addActionListener(this);
        file.add(open);

        save = new JMenuItem("Save");
        save.addActionListener(this);
        file.add(save);

        saveas = new JMenuItem("Save-as");
        saveas.addActionListener(this);
        file.add(saveas);

        file.addSeparator();

        page_setup = new JMenuItem("Page Setup");
        page_setup.addActionListener(this);
        file.add(page_setup);

        print = new JMenuItem("Print");
        print.addActionListener(this);
        file.add(print);

        file.addSeparator();

        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        file.add(exit);

        //Edit
        edit = new JMenu("Edit");

        cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        edit.add(cut);

        copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        edit.add(copy);

        paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        edit.add(paste);

        edit.addSeparator();

        replace = new JMenuItem("Replace");
        replace.addActionListener(this);
        edit.add(replace);

        edit.addSeparator();

        date_time = new JMenuItem("Date-Time");
        date_time.addActionListener(this);
        edit.add(date_time);

        //Format
        format = new JMenu("Format");

        font = new JMenuItem("Font");
        font.addActionListener(this);
        format.add(font);

        format.addSeparator();

        font_color = new JMenuItem("Font Color");
        font_color.addActionListener(this);
        format.add(font_color);

        textarea_color = new JMenuItem("Textarea Color");
        textarea_color.addActionListener(this);
        format.add(textarea_color);

        //Help
        help = new JMenu("Help");
        help.addActionListener(this);

        menubar.add(file);
        menubar.add(edit);
        menubar.add(format);
        menubar.add(help);
        return menubar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == neww) {
            setNeww();
        }
        if (e.getSource() == open) {
            setOpen();
        }
        if (e.getSource() == save) {
            setSave();
        }
        if (e.getSource() == saveas) {
            setSaveas();
        }
        if (e.getSource() == page_setup) {
            setPageSetup();
        }
        if (e.getSource() == print) {
            setPrint();
        }
        if (e.getSource() == exit) {
            System.exit(0);
        }
        if (e.getSource() == cut) {
            textArea.cut();
        }
        if (e.getSource() == copy) {
            textArea.copy();
        }
        if (e.getSource() == paste) {
            textArea.paste();
        }
        if (e.getSource() == replace) {
            setReplace();
        }
        if (e.getSource() == jb) {
            replace();
        }
        if (e.getSource() == date_time) {
            setDate_time();
        }
        if (e.getSource() == font) {
            setFont();
        }
        if (e.getSource() == font_color) {
            setFontColor();
        }
        if (e.getSource() == textarea_color) {
            setTextAreaColor();
        }
    }

    public void setNeww() {
        String text = textArea.getText();
        if (!text.equals("")) {
            int i = JOptionPane.showConfirmDialog(frame, "Do you want to save this file?");
            if (i == 0) {
                setSaveas();
                if (!frame.getTitle().equals(title)) {
                    setTitle(title);
                    textArea.setText("");
                }
            } else if (i == 1)
                textArea.setText("");
            else
                textArea.setText(text);
        }
    }

    public void setOpen() {
        JFileChooser fileChooser = new JFileChooser();
        int i = fileChooser.showOpenDialog(fileChooser);
        if (i == 0) {
            try (
                    FileInputStream fis = new FileInputStream(fileChooser.getSelectedFile())
            ) {
                textArea.setText("");

                int x;
                while ((x = fis.read()) != -1) {
                    textArea.append(String.valueOf((char) x));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                setTitle(fileChooser.getSelectedFile().getName());
            }
        } else {
            JOptionPane.showMessageDialog(frame, "File Not Found", "Open File", JOptionPane.WARNING_MESSAGE);
        }

    }

    public void setSave() {
        if (frame.getTitle().equals(title)) {
            setSaveas();
        } else {
            try (
                    FileOutputStream fos = new FileOutputStream(filee);
            ) {
                String text = textArea.getText();
                byte[] b = text.getBytes();
                fos.write(b);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                setTitle(filee.getName());
            }
        }
    }

    public void setSaveas() {
        JFileChooser fileChooser = new JFileChooser();
        int i = fileChooser.showSaveDialog(fileChooser);
        if (i == 0) {
            try (
                    FileOutputStream fos = new FileOutputStream(filee);
            ) {
                String text = textArea.getText();
                byte[] b = text.getBytes();

                filee = fileChooser.getSelectedFile();
                fos.write(b);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                setTitle(fileChooser.getSelectedFile().getName());
            }
        } else {
            JOptionPane.showMessageDialog(frame, "File Not Saved", "Save File", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void setPageSetup() {
        PrinterJob pj = PrinterJob.getPrinterJob();
        PageFormat pf = pj.pageDialog(pj.defaultPage());
    }

    public void setPrint() {
        PrinterJob pj = PrinterJob.getPrinterJob();
        if (pj.printDialog()) {
            try {
                pj.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setReplace() {
        jf = new JFrame("Replace");
        jf.setSize(500, 300);
        jf.setLayout(null);
        jf.setLocation(500, 250);

        JLabel jl1 = new JLabel("Find What: ");
        jl1.setBounds(50, 50, 80, 30);
        jf.add(jl1);

        jt1 = new JTextField();
        jt1.setBounds(120, 50, 200, 30);
        jf.add(jt1);

        JLabel jl2 = new JLabel("Replace With: ");
        jl2.setBounds(50, 100, 80, 30);
        jf.add(jl2);

        jt2 = new JTextField();
        jt2.setBounds(120, 100, 200, 30);
        jf.add(jt2);

        jb = new JButton("Replace");
        jb.addActionListener(this);
        jb.setBounds(200, 150, 100, 40);
        jf.add(jb);

        jf.setVisible(true);
    }

    public void replace() {
        String find_what = jt1.getText();
        String replace_with = jt2.getText();
        String text = textArea.getText();
        String new_text = text.replace(find_what, replace_with);
        textArea.setText(new_text);

        jf.setVisible(false);
    }

    public void setDate_time() {
        LocalDateTime ldt = LocalDateTime.now();
        String datetime = ldt.format(DateTimeFormatter.ISO_DATE_TIME);
        textArea.append(datetime);
    }

    public void setTitle(String title) {
        frame.setTitle(title);
    }

    public void setFont() {

    }

    public void setFontColor() {
        Color c = JColorChooser.showDialog(frame, "Select Font Color", Color.BLACK);
        textArea.setForeground(c);
    }

    public void setTextAreaColor() {
        Color c = JColorChooser.showDialog(frame, "Select Text Area Color", Color.WHITE);
        textArea.setBackground(c);
    }

    public static void main(String[] args) {
        new NotePad();
    }
}
