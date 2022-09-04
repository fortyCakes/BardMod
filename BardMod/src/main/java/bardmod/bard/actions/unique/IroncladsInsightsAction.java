package bardmod.bard.actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class IroncladsInsightsAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private int energyOnUse;
    private boolean alwaysTopCard;

    public IroncladsInsightsAction(AbstractPlayer p, boolean alwaysTopCard) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            if (p.drawPile.size() == 0)
            {
                this.isDone = true;
                return;
            }
            else if (p.drawPile.size() == 1 || alwaysTopCard)
            {
                this.isDone = true;
                p.drawPile.moveToExhaustPile(p.drawPile.getTopCard());
                return;
            }
            else {
                AbstractDungeon.gridSelectScreen.open(p.drawPile, 1,  "Select card to exhaust", false, false, false, true);
                tickDuration();
                return;
            }
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() > 0) {
            for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                p.drawPile.moveToExhaustPile(card);
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.isDone = true;
        }
        tickDuration();

    }
}