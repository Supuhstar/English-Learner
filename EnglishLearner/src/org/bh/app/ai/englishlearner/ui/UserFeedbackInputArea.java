package org.bh.app.ai.englishlearner.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.bh.app.ai.englishlearner.evt.UserAIRatingBroadcaster;
import org.bh.app.ai.englishlearner.evt.UserAIRatingListener;

/**
 * UserFeedbackInputArea, made for English Learner, is copyright BHStudios ©2016 BH-1-PS <hr/>
 * Allows the user to rate the output of an AI. This class was written to perform extremely fast, and so doesn't
 * represent the best design paradigms.
 *
 * @author Kyli Rouge of BHStudios
 * @since 2016-03-30
 */
public class UserFeedbackInputArea extends JPanel implements UserAIRatingBroadcaster {

    private static final Color BAD_COLOR = new Color(0xBB2277),
            GOOD_COLOR = new Color(0x22BB77),
            TEXT_COLOR = new Color(0xF5F5F5),
            TEXT_STROKE_COLOR = new Color(0x222222),
            CURSOR_INDICATOR_COLOR = new Color(0xC0_FFFFFF, true),
            DISABLED_BACKGROUND_COLOR = new Color(0x9E9E9E),
            DISABLED_FOREGROUND_COLOR = new Color(0x607D8B);
    private static boolean checkedForGraphics2D = false, detectedGraphics2D = false;
    private static final String BAD_TEXT = "Horrid", GOOD_TEXT = "Perfect", FONT_NAME, IDEAL_FONT_NAMES[] = {"Roboto", "Roboto Black", "Helvetica Neue", "Helvetica Neue Black", "Helvetica", "Arial", Font.SANS_SERIF};
    private static final int DEFAULT_FONT_SIZE = 16;
    private static final NumberFormat PERCENTAGE_FORAMT = NumberFormat.getPercentInstance();
    private final LinkedList<UserAIRatingListener> userAIRatingListeners = new LinkedList<>();
    private final JLabel horridLabel = new JLabel(BAD_TEXT);
    private final JLabel amazingLabel = new JLabel("Amazing");
    private int lastRepaintedCursorStripeXPosition;
    @SuppressWarnings("FieldMayBeFinal")
    private double scale = 1;

    private static final double LEFTMOST_RATING = -1, RIGHTMOST_RATING = 1;


    static {
        Font foundFont = null;
        for (String eachFontName : IDEAL_FONT_NAMES) {
            foundFont = Font.getFont(eachFontName);
            if (foundFont != null) {
                break;
            }
        }
        if (null == foundFont) {
            FONT_NAME = Font.SANS_SERIF;
        } else {
            FONT_NAME = foundFont.getFontName();
        }
        PERCENTAGE_FORAMT.setMinimumFractionDigits(1);
        PERCENTAGE_FORAMT.setMinimumFractionDigits(0);
    }

    public UserFeedbackInputArea() {
        addSelfListeners();
        initGUI();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        repaint();
    }

    private void addSelfListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                repaintCursorStripe(e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                processUserRatingAtLocation(e.getPoint());
            }
        });
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent e) {
                repaintCursorStripe(e.getPoint());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                repaintCursorStripe(e.getPoint());
            }
        });
        listenForUserAIRating(userRating -> {
            System.out.println("User rated this as " + userRating);
        });
    }

    @Override
    public void listenForUserAIRating(UserAIRatingListener listener) {
        userAIRatingListeners.add(listener);
    }

    @Override
    @SuppressWarnings("null")
    public void paint(Graphics g) {
        if (isEnabled()) {
            drawGradient(g);
            drawCursorPositionIndicator(g);
            drawExtremeLabels(g);
        } else {
            drawDisabledPlaceholder(g);
        }

        g.setColor(new Color(0, true));
        super.paint(g);
    }

    private void repaintCursorStripe(Point cursorPosition) {
        repaint();
        /*repaint(lastRepaintedCursorStripeXPosition - 128, 0, lastRepaintedCursorStripeXPosition + 128, getHeight());
        repaint(cursorPosition.x - 128, 0, cursorPosition.x + 128, getHeight());*/
        lastRepaintedCursorStripeXPosition = cursorPosition.x;
    }

    private void processUserRatingAtLocation(Point point) {
        final boolean wasEnabledBefore = isEnabled();
        if (!wasEnabledBefore) {
            return;
        }
        setEnabled(false);
        try {
            // Let's say the leftmost edge is -1 and the rightmost edge is +1.

            final double x = point.x;
            final double width = getWidth();
            final double percentToRight = x / width;
            final double rating = (percentToRight - 0.5) * 2;

            userAIRatingListeners.parallelStream().filter(t -> null != t).forEach(t -> t.userDidRateAI(rating));
        } catch (Throwable t) {
            throw t;
        } finally {
            setEnabled(wasEnabledBefore);
        }
    }


//    private int colorGradientTempRed, colorGradientTempGreen, colorGradientTempBlue;
    private static Color getColorInGradient(Color color1, Color color2, double ratio) {
        return new Color(
                (int) (color2.getRed() * ratio + color1.getRed() * (1 - ratio)),
                (int) (color2.getGreen() * ratio + color1.getGreen() * (1 - ratio)),
                (int) (color2.getBlue() * ratio + color1.getBlue() * (1 - ratio)));
    }

    private void initGUI() {
        setBackground(new Color(0, true));
        setFont(new Font(FONT_NAME, Font.BOLD, DEFAULT_FONT_SIZE));
        Dimension minSize = new Dimension((int) Math.round(64 * scale), (int) Math.round(64 * scale));
        setMinimumSize(minSize);
        setPreferredSize(minSize);
    }

    private void drawGradient(Graphics g) {
        final double totalWidth = getWidth();
        final int height = getHeight();

        for (int x = (int) totalWidth; x-- > 0;) {
            g.setColor(getColorInGradient(BAD_COLOR, GOOD_COLOR, x / totalWidth));
            g.drawLine(x, 0, x, height);
        }
    }

    @SuppressWarnings("null")
    private void drawExtremeLabels(Graphics g) {
        final double percentageStringXPadding = scale * 8,
                fontSize = this.getFont().getSize2D(),
                scaleTimesFontSize = scale * fontSize,
                totalWidth = getWidth(),
                textLabelsYPosition = scale * (fontSize + 8),
                goodTextScaledStringWidth;
        final int scaledFontSizeInt = (int) Math.round(scaleTimesFontSize);

        g.setColor(TEXT_COLOR);
        g.setFont(new Font(this.getFont().getFontName(), Font.PLAIN, scaledFontSizeInt));
        if (shouldUseGraphics2D(g)) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

//            g2d.setColor(TEXT_STROKE_COLOR);
//            g2d.setStroke(new BasicStroke((float) scale));

            g2d.drawString(BAD_TEXT, (float) percentageStringXPadding, (float) textLabelsYPosition);
            goodTextScaledStringWidth = g2d.getFontMetrics().getStringBounds(GOOD_TEXT, g).getWidth();
            g2d.drawString(GOOD_TEXT, (float) (totalWidth - percentageStringXPadding - goodTextScaledStringWidth), (float) textLabelsYPosition);
        } else {
            g.drawString(BAD_TEXT, (int) Math.round(percentageStringXPadding), (int) Math.round(textLabelsYPosition));
            goodTextScaledStringWidth = g.getFontMetrics().getStringBounds(GOOD_TEXT, g).getWidth();
            g.drawString(GOOD_TEXT, (int) Math.round(totalWidth - percentageStringXPadding - goodTextScaledStringWidth), (int) Math.round(textLabelsYPosition));
        }
    }

    private void drawCursorPositionIndicator(Graphics g) {
        final Point rawCursorPosition = MouseInfo.getPointerInfo().getLocation(),
                selfScreenPosition = getLocationOnScreen();
        final double width = getWidth(),
                height = getHeight(),
                cursorX = rawCursorPosition.getX() - selfScreenPosition.getX(),
                cursorY = rawCursorPosition.getY() - selfScreenPosition.getY(),
                percentageStringXPadding = 8 * scale,
                fontSize = this.getFont().getSize2D(),
                scaleTimesFontSize = scale * fontSize;
        final int scaledFontSizeInt = (int) Math.round(scaleTimesFontSize);
        boolean isCursorOffSelf = cursorX <= 0 || cursorY <= 0 || cursorX >= width || cursorY >= height;
        if (isCursorOffSelf) {
            return;
        }

        g.setColor(CURSOR_INDICATOR_COLOR);
        g.setFont(new Font(this.getFont().getFontName(), Font.PLAIN, scaledFontSizeInt));

        final int cursorXInt = (int) Math.round(cursorX), cursorYInt = (int) Math.round(cursorY);
        final String percentageString = PERCENTAGE_FORAMT.format(cursorX / width);
        final Rectangle2D percentageStringSize = g.getFontMetrics().getStringBounds(percentageString, g);
        final boolean drawPercentageOnLeft = cursorX > (getWidth() / 2);


        if (shouldUseGraphics2D(g)) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);

            g2d.drawLine(cursorXInt, 0, cursorXInt, getHeight());
            if (drawPercentageOnLeft) {
                g2d.drawString(percentageString, (float) (cursorX - percentageStringSize.getWidth() - percentageStringXPadding), (float) cursorY);
            } else {
                g2d.drawString(percentageString, (float) (cursorX + percentageStringXPadding), (float) cursorY);
            }
        } else if (drawPercentageOnLeft) {
            g.drawString(percentageString, (int) Math.round(cursorX - percentageStringSize.getWidth() - percentageStringXPadding), cursorYInt);
        } else {
            g.drawString(percentageString, (int) Math.round(cursorX + percentageStringXPadding), cursorYInt);
        }
        g.drawLine(cursorXInt, 0, cursorXInt, getHeight());
    }

    private void drawDisabledPlaceholder(Graphics g) {
        final double width = getWidth(), height = getHeight();
        final int widthInt = (int) Math.round(width), heightInt = (int) Math.round(height);
        final double fontSize = this.getFont().getSize2D(),
                scaleTimesFontSize = scale * fontSize;
        final int scaledFontSizeInt = (int) Math.round(scaleTimesFontSize);

        g.setColor(DISABLED_BACKGROUND_COLOR);
        g.fillRect(0, 0, widthInt, heightInt);

        g.setColor(DISABLED_FOREGROUND_COLOR);
        g.setFont(new Font(this.getFont().getFontName(), Font.PLAIN, scaledFontSizeInt));
        final Rectangle2D stringSize = g.getFontMetrics().getStringBounds(DISABLED_TEXT, g);
        if (shouldUseGraphics2D(g)) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
            g2d.drawString(DISABLED_TEXT,
                    (float) ((width / 2) - (stringSize.getWidth() / 2)),
                    (float) ((height / 2) + (stringSize.getHeight() / 2)));
        } else {
            g.drawString(DISABLED_TEXT,
                    (int) Math.round((width / 2) - (stringSize.getWidth() / 2)),
                    (int) Math.round((height / 2) + (stringSize.getHeight() / 2)));
        }
    }
    private static final String DISABLED_TEXT = "Nothing to rate";

    private static boolean shouldUseGraphics2D(Graphics g) {
        return checkedForGraphics2D
                ? detectedGraphics2D
                : ((checkedForGraphics2D = true) && (detectedGraphics2D = g instanceof Graphics2D));
    }
}
