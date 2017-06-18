package pixel.bus.gui.dialog;

import pixel.bus.dao.DaoFactory;
import pixel.bus.dao.IPassengerDao;
import pixel.bus.gui.renderer.CustomCellRenderer;
import pixel.bus.model.Passenger;
import pixel.bus.model.gui.PassengerTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ActionLogDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonRefresh;
    private JButton buttonClose;
    private JTable tableAllPassengers;

    private IPassengerDao passengerDao = DaoFactory.getInstance(IPassengerDao.class);
    private List<Passenger> passengerList = passengerDao.getAll();
    private PassengerTableModel pmodel = new PassengerTableModel(passengerList);

    public ActionLogDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonRefresh);


        tableAllPassengers.setModel(pmodel);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(pmodel);
        sorter.setSortKeys(sortKeys);
        tableAllPassengers.setRowSorter(sorter);

//
        tableAllPassengers.setDefaultRenderer(Object.class, new CustomCellRenderer());
        tableAllPassengers.setDefaultRenderer(Integer.class, new CustomCellRenderer());


        buttonRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onRefresh();
            }
        });

        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClose();
            }
        });

        // call onClose() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onClose();
            }
        });

        // call onClose() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onRefresh() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                passengerList.removeAll(passengerList);
                passengerList.addAll(passengerDao.getAll());

                pmodel.fireTableDataChanged();
            }
        });
    }

    private void onClose() {
        dispose();
    }

    public static void main(String[] args) {
        ActionLogDialog dialog = new ActionLogDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
