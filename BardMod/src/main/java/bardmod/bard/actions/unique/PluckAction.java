package bardmod.bard.actions.unique;

import bardmod.bard.BardCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class PluckAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final int cardsToGet;

    public PluckAction(AbstractPlayer p, int cardsToGet) {
        this.cardsToGet = cardsToGet;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            AbstractCard[] selectedCards = null;
            CardGroup cards = new CardGroup(CardGroup.CardGroupType.CARD_POOL);

            for (AbstractCard card : p.discardPile.group) {
                if (card.hasTag(BardCardTags.NOTE_A) || card.hasTag(BardCardTags.NOTE_B) || card.hasTag(BardCardTags.NOTE_C)) {
                    cards.addToBottom(card);
                }
            }
            for (AbstractCard card : p.drawPile.group) {
                if (card.hasTag(BardCardTags.NOTE_A) || card.hasTag(BardCardTags.NOTE_B) || card.hasTag(BardCardTags.NOTE_C)) {
                    cards.addToBottom(card);
                }
            }

            if (cards.size() == 0)  {
                this.isDone = true;
                return;
            }
            else if (cards.size() <= 1) {
                addCardsToHand(cards.group);
                this.isDone = true;
                return;
            }
            else {
                AbstractDungeon.gridSelectScreen.open(cards, cardsToGet,  "Select cards to put in your hand", false, false, false, false);
                tickDuration();
                return;
            }
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() > 0) {
            addCardsToHand(AbstractDungeon.gridSelectScreen.selectedCards);

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isDone = true;
        }
        tickDuration();

    }

    private void addCardsToHand(ArrayList<AbstractCard> cards) {
        for (AbstractCard card : cards)
        {
            addCardToHand(card);
        }
    }

    private void addCardToHand(AbstractCard card) {
        if (p.hand.size() == 10)
        {
            if (p.drawPile.contains(card)) {
                this.p.drawPile.moveToDiscardPile(card);
                this.p.createHandIsFullDialog();
            }
        }
        else {
            card.unhover();
            card.lighten(true);
            card.setAngle(0.0F);
            card.drawScale = 0.12F;
            card.targetDrawScale = 0.75F;
            card.current_x = CardGroup.DRAW_PILE_X;
            card.current_y = CardGroup.DRAW_PILE_Y;
            if (p.drawPile.contains(card)) {
                this.p.drawPile.removeCard(card);
            } else if (p.discardPile.contains(card)) {
                this.p.discardPile.removeCard(card);
            }

            AbstractDungeon.player.hand.addToTop(card);
            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.applyPowers();
        }
    }
}