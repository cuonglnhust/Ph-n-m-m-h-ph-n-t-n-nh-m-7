package list.caculate;

import list.constant.HistoryElementConstant;

public class HistoryCalculateElement {
    private int id;

    public HistoryCalculateElement(int id) {
        this.id = id;
    }

    public int getBottomLine() {
        return HistoryElementConstant.FIRST_ELEMENT_Y + HistoryElementConstant.ELEMENT_HEIGHT * (id + 1);
    }

    public int getTextElementY(){
        return HistoryElementConstant.FIRST_ELEMENT_Y + HistoryElementConstant.ELEMENT_HEIGHT * id;
    }
}
