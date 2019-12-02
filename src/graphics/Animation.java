package graphics;

import java.awt.image.BufferedImage;

public class Animation {

    private static final int SPEED = 200;

    // thời gian để thực hiện Animation
    private long timer, lastTime;
    // số thứ tự ảnh trong mảng và tốc độ hiển thị Animation
    private int index;
    // mảng các ảnh để tạo thành Animation
    private BufferedImage[] anim;
    // đếm số lần lặp lại
    private int count;
    // số lần lặp lại
    private int repeatTime;
    // đánh dấu kết thúc animation
    private boolean end;

    public Animation(BufferedImage[] anim, long period) {
        this.anim = anim;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public Animation(BufferedImage[] anim, int repeatTime) {
        this.anim = anim;
        // chỉ số của mảng ảnh
        index = 0;
        // biến đặt hẹn giờ
        timer = 0;
        this.repeatTime = repeatTime;
    }

    // Vẽ Animation
    public void runTimerAnimation() {
        // thời gian hiện tại
        timer = System.currentTimeMillis();
        // nếu hiện tại - quá khứ = thời gian để đổi ảnh
        if (timer - lastTime >= SPEED) {
            // hiện tại trở thành quá khứ cho lần đổi ảnh kế tiếp
            lastTime = System.currentTimeMillis();
            // bộ đếm về mặc định
            timer = 0;
            // đổi ảnh
            // nếu ảnh có chỉ số là cuối cùng trong mảng thì chuyển về phần tử đầu tiên
            if (index == anim.length - 1) {
                index = 0;
            }
            // nếu không là phần tử cuối thì tăng lên 1 đơn vị
            else
                index++;
            // tăng biến đếm số lần lặp lại lên 1 đơn vị
            count++;
        }
        // nếu đã thực hiện đủ số lần lặp lại, đánh dấu kết thúc animation
        if (count == repeatTime){
            end = true;
            count = 0;
        }
    }

    public BufferedImage getCurrentImage() {
        return anim[index];
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
