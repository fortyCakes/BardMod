package bardmod.bard.actions.unique;

import bardmod.bard.powers.ChorusPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class ChorusAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final int energyOnUse;
    private final boolean freeToPlayOnce;

    public ChorusAction(AbstractPlayer p, int energyOnUse, boolean freeToPlayOnce) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
        this.energyOnUse = energyOnUse;
        this.freeToPlayOnce = freeToPlayOnce;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            int effect = EnergyPanel.totalCount;
            if (this.energyOnUse != -1) {
                effect = this.energyOnUse;
            }

            if (this.p.hasRelic("Chemical X")) {
                effect += 2;
                this.p.getRelic("Chemical X").flash();
            }

            AbstractCard selectedCard = null;
            CardGroup cards = new CardGroup(CardGroup.CardGroupType.CARD_POOL);

            for (AbstractCard card : p.hand.group) {
                if (card.costForTurn <= effect || card.cost == -1) {
                    cards.addToBottom(card);
                }
            }

            if (cards.size() == 0)  {
                this.isDone = true;
                spendEnergy();
                return;
            }
            else if (cards.size() == 1) {
                resolveChorus(cards.getTopCard());
                this.isDone = true;
                return;
            }
            else {
                AbstractDungeon.gridSelectScreen.open(cards, 1,  "Select a card to Chorus", false, false, false, false);
                tickDuration();
                return;
            }
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() > 0) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                resolveChorus(c);
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isDone = true;
        }
        tickDuration();

    }

    private void resolveChorus(AbstractCard selectedCard) {
        addToBot(new ApplyPowerAction(p, p, new ChorusPower(p, selectedCard)));
        addToBot(new ExhaustSpecificCardAction(selectedCard, p.hand, false));
        spendEnergy();
    }

    private void spendEnergy() {
        if (!this.freeToPlayOnce) {
            this.p.energy.use(EnergyPanel.totalCount);
        }
    }
}