package graphics;

import java.awt.image.BufferedImage;

public class Animation {

    private static final int SPEED = 200;

    // thời gian bắt đầu animation
    private long beginTime;

    // thời gian để thực hiện Animation
    private long timer, lastTime;
    // số thứ tự ảnh trong mảng và tốc độ hiển thị Animation
    private int index;
    // mảng các ảnh để tạo thành Animation
    private BufferedImage[] anim;

    public Animation(BufferedImage[] anim, long period) {
        this.anim = anim;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
        beginTime = System.currentTimeMillis();
    }

    // Vẽ Animation
    public void DrawAnimation() {
        // timer là chênh lệch thời gian giữa hệ thống hiện tại với lúc đo trước đó
        timer += System.currentTimeMillis() - lastTime;
        // lặp lại thời gian đo lần cuối hệ thống để cho thời điểm đo tiếp theo
        lastTime = System.currentTimeMillis();
        // Nếu thời gian giữa hai lần đo mà lớn hơn
        // tốc độ chuyển ảnh thì chuyển sang ảnh khác
        if (timer > SPEED) {
            index++;
            // đặt lại timer
            timer = 0;
            // Nếu chuyển hết ảnh trong Anim thì chuyển về ảnh ban đầu
            if (index >= anim.length) {
                index = 0;
            }
        }
    }

    public BufferedImage getCurrentImage() {
        return anim[index];
    }
}
