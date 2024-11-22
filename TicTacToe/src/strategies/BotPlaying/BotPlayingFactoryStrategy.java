package strategies.BotPlaying;

import models.BotDifficultyLevel;
import strategies.BotPlayingStrategy;

public class BotPlayingFactoryStrategy {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel) {
        return new EasyBotPlayingStrategy();
    }
}
