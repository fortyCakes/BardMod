package bardmod.bard;

import bardmod.bard.cards.TheBardsLament;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.ArrayList;

public class StoryHelper {
    public static AbstractCard generateStoryCard(boolean allowLament) {
        ArrayList<AbstractCard> validCards = new ArrayList<>();
        for(AbstractCard card : CardLibrary.getCardList(LibraryTypeEnum.BARD_ORANGE)) {
            if (card.hasTag(BardCardTags.STORY) && (allowLament || !card.cardID.equals(TheBardsLament.ID)))
            {
                validCards.add(card);
            }
        }
        if (validCards.size() > 0)
        {
            int index = AbstractDungeon.cardRng.random(validCards.size());
            return validCards.get(index);
        }
        else {
            return null;
        }
    }
}
