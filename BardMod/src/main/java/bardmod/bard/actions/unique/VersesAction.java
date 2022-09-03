package bardmod.bard.actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class VersesAction extends AbstractGameAction {
    private final AbstractPlayer p;

    public VersesAction(AbstractPlayer p) {
        this.p = AbstractDungeon.player;

    }

    public void update() {
        ArrayList<String> uniqueCards = new ArrayList<>();
        
        for (AbstractCard card : p.hand.group)
        {
            addToListOrExhaust(uniqueCards, card, p.hand);
        }
        for (AbstractCard card : p.drawPile.group)
        {
            addToListOrExhaust(uniqueCards, card, p.drawPile);
        }
        for (AbstractCard card : p.discardPile.group)
        {
            addToListOrExhaust(uniqueCards, card, p.discardPile);
        }
    }

    private void addToListOrExhaust(ArrayList<String> uniqueCards, AbstractCard card, CardGroup from) {
        if (uniqueCards.contains(card.cardID + (card.upgraded ? "+" : "")))
        {
            addToBot(new ExhaustSpecificCardAction(card, from));
        }
        else {
            uniqueCards.add(card.cardID + (card.upgraded ? "+" : ""));
        }
    }

}