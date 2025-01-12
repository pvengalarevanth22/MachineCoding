package strategies;

import models.SlotAllotmentStrategyType;

public class SlotAllotmentStrategyFactory {

    public static SlotAllotmentStrategy getSlotAllotmentStrategy(SlotAllotmentStrategyType slotAllotmentStrategyType) {
        if(slotAllotmentStrategyType == SlotAllotmentStrategyType.RANDOM) {
            return new RandomSlotAllotmentStrategy();
        }
        return null;
    }
}
