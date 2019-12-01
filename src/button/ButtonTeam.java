package button;

import constant.TeamType;
import graphics.CreateImage;
import list.graphics.TextGraphicsElement;
import main.Handler;
import state.ChoseTeamState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;

public class ButtonTeam extends Button {

    // thông tin người dùng click vào nút n
    private int id;
    private String name;

    private BufferedImage team;
    private boolean isChose;
    private boolean canChose;
    private TextGraphicsElement text;
    private TeamType teamType;
    private ChoseTeamState choseTeamState;

    public ButtonTeam(int x, int y, BufferedImage team, TeamType teamType, ChoseTeamState choseTeamState) {
        super(x, y);
        this.team = team;
        text = new TextGraphicsElement(x, getBound().width, getBound().height, Handler.getInstance().getName(),
                y + getBound().height, "BLACKR");
        this.teamType = teamType;
        this.choseTeamState = choseTeamState;
        this.canChose = true;
        this.id = -1;
        this.name = "";
    }

    public void tick() {
        if (isOver()) {
            // nếu click
            if (Handler.getInstance().getMouse().isLeftClick() || Handler.getInstance().getMouse().isRightClick()) {
                // nếu chưa bị đội khác chọn mất (có thể chọn)
                if (canChose) {
                    // nếu chưa chọn ô nào
                    if (choseTeamState.getChoseTeam() == TeamType.NONE) {
                        Handler.getInstance().getMouse().setDefaultClick();
                        // chuyển trạng thái nút thành đã chọn
                        isChose = true;
                        // trạng thái người chơi là đã chọn đội màu teamtype
                        choseTeamState.setChoseTeam(teamType);
                        // cập nhật id cho nút
                        id = Handler.getInstance().getId();
                        // nếu là Player thì cập nhật lên Server
                        if (Handler.getInstance().getClientPlayer() != null) {
                            try {
                                System.out.println("Client Click");
                                Handler.getInstance().getClientPlayer().getChoseTeamServer()
                                        .showChoseTeam(Handler.getInstance().getName(), teamType, Handler.getInstance().getId());
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                        // nếu là Server thì broadcast thông tin này sang các máy khác
                        else if (Handler.getInstance().getServerPlayer() != null) {
                            try {
                                System.out.println("Server Click");
                                Handler.getInstance().getServerPlayer().getChoseTeamServerImp()
                                        .broadcastChose(Handler.getInstance().getId(), Handler.getInstance().getName(), teamType);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    // nếu đã chọn một nút rồi và muốn bỏ chọn
//                    else if (choseTeamState.getChoseTeam() == teamType) {
//                        Handler.getInstance().getMouse().setDefaultClick();
//                        // chuyển trạng thái người chơi chưa chọn đội
//                        choseTeamState.setChoseTeam(TeamType.NONE);
//                        // chuyển trạng thái của nút thành chưa được chọn
//                        isChose = false;
//                        // nếu là Player thì cập nhật lên Server
//                        if (Handler.getInstance().getClientPlayer() != null) {
//                            try {
//                                Handler.getInstance().getClientPlayer().getChoseTeamServer()
//                                        .cancelChoseTeam(Handler.getInstance().getId(), Handler.getInstance().getName(), teamType);
//                            } catch (RemoteException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        // nếu là Server thì broadcast cho các Client
//                        else if (Handler.getInstance().getServerPlayer() != null) {
//                            try {
//                                Handler.getInstance().getServerPlayer().getChoseTeamServerImp()
//                                        .broadcastCancel(Handler.getInstance().getId(), Handler.getInstance().getName(), teamType);
//                            } catch (RemoteException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(team, x, y, null);
        if (isOver()) {
            g.setColor(new Color(145, 143, 135, 90));
            g.fillRect(getBound().x, getBound().y, getBound().width, getBound().height);
        }
        if (isChose || !canChose) {
            g.setColor(new Color(145, 143, 135, 90));
            g.fillRect(getBound().x, getBound().y, getBound().width, getBound().height);
            text.render(g);
        }
    }

    @Override
    public Rectangle getBound() {
        int width = CreateImage.blueBackground.getWidth();
        int height = CreateImage.blueBackground.getHeight();
        return new Rectangle(x, y, width, height);
    }

    public void setCanChose(boolean canChose) {
        this.canChose = canChose;
    }

    public void setName(String name) {
        this.name = name;
        this.text.setText(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdDefault() {
        id = -1;
    }

    public void setNameDefault() {
        name = "";
    }

    public String getName() {
        return name;
    }

    public boolean isChose() {
        return isChose;
    }

    public boolean isCanChose() {
        return canChose;
    }

    public TeamType getTeamType() {
        return teamType;
    }
}
