package bardmod.bard.actions.unique;

import bardmod.bard.powers.SadnessPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class TripleSadAction extends AbstractGameAction {
    public TripleSadAction(AbstractCreature target, AbstractCreature source) {
        this.target = target;
        this.source = source;
        this.actionType = ActionType.DEBUFF;
        this.attackEffect = AttackEffect.FIRE;
    }

    public void update() {
        if (this.target != null && this.target.hasPower("Sadness")) {
            addToTop((AbstractGameAction)new ApplyPowerAction(this.target, this.source, new SadnessPower(this.target, (this.target.getPower("Sadness")).amount),
                    (this.target.getPower("Sadness")).amount * 2));
        }
        this.isDone = true;
    }
}