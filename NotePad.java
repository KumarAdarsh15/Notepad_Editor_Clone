import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class NotePad implements ActionListener {
    JFrame frame, replace_frame, font_frame;
    JMenuBar menubar;
    JMenu file, help;
    JMenuItem neww, open, save, saveas, page_setup, print, exit;
    JMenu edit;
    JMenuItem cut, copy, paste, replace, date_time;
    JMenu format;
    JMenuItem font, font_color, textarea_color;
    JMenu view, zoom;
    JMenuItem zoomin, zoomout;
    JCheckBoxMenuItem word_wrap;
    JTextArea textArea;
    String title = "Untitled_Notepad";
    File filee;
    JTextField jt1, jt2;
    JButton jb, ok;
    JComboBox cb_font_family, cb_font_style, cb_font_size;

    public NotePad() {
        invokeGUI();
    }

    private void invokeGUI() {
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
        neww.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
        neww.addActionListener(this);
        file.add(neww);

        open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
        open.addActionListener(this);
        file.add(open);

        save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
        save.addActionListener(this);
        file.add(save);

        saveas = new JMenuItem("Save-as");
        saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, 3));
        saveas.addActionListener(this);
        file.add(saveas);

        file.addSeparator();

        page_setup = new JMenuItem("Page Setup");
        page_setup.addActionListener(this);
        file.add(page_setup);

        print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_DOWN_MASK));
        print.addActionListener(this);
        file.add(print);

        file.addSeparator();

        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        file.add(exit);

        //Edit
        edit = new JMenu("Edit");

        cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_DOWN_MASK));
        cut.addActionListener(this);
        edit.add(cut);

        copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_DOWN_MASK));
        copy.addActionListener(this);
        edit.add(copy);

        paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_DOWN_MASK));
        paste.addActionListener(this);
        edit.add(paste);

        edit.addSeparator();

        replace = new JMenuItem("Replace");
        replace.setAccelerator(KeyStroke.getKeyStroke('H', InputEvent.CTRL_DOWN_MASK));
        replace.addActionListener(this);
        edit.add(replace);

        edit.addSeparator();

        date_time = new JMenuItem("Date/Time");
        date_time.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 1));
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

        //View
        view = new JMenu("View");

        zoom = new JMenu("Zoom");

        zoomin = new JMenuItem("Zoom in");
        zoomin.addActionListener(this);
        zoom.add(zoomin);

        zoomout = new JMenuItem("Zoom out");
        zoomout.addActionListener(this);
        zoom.add(zoomout);

        view.add(zoom);
        view.addSeparator();

        word_wrap = new JCheckBoxMenuItem("Word Wrap");
        word_wrap.addActionListener(this);
        view.add(word_wrap);

        //Help
        help = new JMenu("Help");
        help.addActionListener(this);

        menubar.add(file);
        menubar.add(edit);
        menubar.add(format);
        menubar.add(view);
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
            setReplaceFrame();
        }
        if (e.getSource() == jb) {
            setreplace();
        }
        if (e.getSource() == date_time) {
            setDate_time();
        }
        if (e.getSource() == font) {
            setFontFrame();
        }
        if (e.getSource() == ok) {
            setFonttoNotepad();
        }
        if (e.getSource() == font_color) {
            setFontColor();
        }
        if (e.getSource() == textarea_color) {
            setTextAreaColor();
        }
        if (e.getSource() == zoomin) {
            Font currentFont = textArea.getFont();
            int newSize = currentFont.getSize() + 2; // Increase the font size by 2 points
            setFontSize(textArea, newSize);
        }
        if (e.getSource() == zoomout) {
            Font currentFont = textArea.getFont();
            int newSize = currentFont.getSize() - 2; // Decrease the font size by 2 points
            setFontSize(textArea, newSize);
        }
        if (e.getSource() == word_wrap) {
            boolean b = word_wrap.getState();
            textArea.setLineWrap(b);
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

    public void setReplaceFrame() {
        replace_frame = new JFrame("Replace");
        replace_frame.setSize(500, 300);
        replace_frame.setLayout(null);
        replace_frame.setLocation(500, 250);


        JLabel jl1 = new JLabel("Find What: ");
        jl1.setBounds(50, 50, 80, 30);
        replace_frame.add(jl1);

        jt1 = new JTextField();
        jt1.setBounds(120, 50, 200, 30);
        replace_frame.add(jt1);

        JLabel jl2 = new JLabel("Replace With: ");
        jl2.setBounds(50, 100, 80, 30);
        replace_frame.add(jl2);

        jt2 = new JTextField();
        jt2.setBounds(120, 100, 200, 30);
        replace_frame.add(jt2);

        jb = new JButton("Replace");
        jb.addActionListener(this);
        jb.setBounds(200, 150, 100, 40);
        replace_frame.add(jb);

        replace_frame.setVisible(true);
    }

    public void setreplace() {
        String find_what = jt1.getText();
        String replace_with = jt2.getText();
        String text = textArea.getText();
        String new_text = text.replace(find_what, replace_with);
        textArea.setText(new_text);

        replace_frame.setVisible(false);
    }

    public void setDate_time() {
        LocalDateTime ldt = LocalDateTime.now();
        String datetime = ldt.format(DateTimeFormatter.ISO_DATE_TIME);
        textArea.append(datetime);
    }

    public void setTitle(String title) {
        frame.setTitle(title);
    }

    public void setFontFrame() {
        font_frame = new JFrame("Font");
        font_frame.setSize(650, 300);
        font_frame.setLocation(450, 250);
        font_frame.setLayout(null);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] font_family = ge.getAvailableFontFamilyNames();
        cb_font_family = new JComboBox(font_family);
        cb_font_family.setBounds(50, 50, 200, 30);
        font_frame.add(cb_font_family);

        String[] font_style = {"PLAIN", "BOLD", "ITALIC"};
        cb_font_style = new JComboBox(font_style);
        cb_font_style.setBounds(300, 50, 100, 30);
        font_frame.add(cb_font_style);

        Integer[] font_size = {8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72};
        cb_font_size = new JComboBox(font_size);
        cb_font_size.setBounds(450, 50, 80, 30);
        font_frame.add(cb_font_size);

        ok = new JButton("OK");
        ok.setBounds(250, 150, 80, 50);
        ok.addActionListener(this);
        font_frame.add(ok);

        font_frame.setVisible(true);
    }

    public void setFonttoNotepad() {
        String font_family = (String) cb_font_family.getSelectedItem();
        String font_style = (String) cb_font_style.getSelectedItem();
        Integer font_size = (Integer) cb_font_size.getSelectedItem();

        int f_style = 0;
        if (font_style.equals("PLAIN")) {
            f_style = Font.PLAIN;
        } else if (font_style.equals("BOLD")) {
            f_style = Font.BOLD;
        } else {
            f_style = Font.ITALIC;
        }

        Font f = new Font(font_family, f_style, font_size);

        textArea.setFont(f);
        font_frame.setVisible(false);

    }

    public void setFontColor() {
        Color c = JColorChooser.showDialog(frame, "Select Font Color", Color.BLACK);
        textArea.setForeground(c);
    }

    public void setTextAreaColor() {
        Color c = JColorChooser.showDialog(frame, "Select Text Area Color", Color.WHITE);
        textArea.setBackground(c);
    }

    public static void setFontSize(JTextArea textArea, int size) {
        Font currentFont = textArea.getFont();
        textArea.setFont(new Font(currentFont.getName(), currentFont.getStyle(), size));
    }

    public static void main(String[] args) {
        new NotePad();
    }
}
