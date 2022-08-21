package bardmod.bard.actions.unique;

import bardmod.bard.powers.SadnessPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class DoubleSadAction extends AbstractGameAction {
    public DoubleSadAction(AbstractCreature target, AbstractCreature source) {
        this.target = target;
        this.source = source;
        this.actionType = AbstractGameAction.ActionType.DEBUFF;
        this.attackEffect = AbstractGameAction.AttackEffect.FIRE;
    }

    public void update() {
        if (this.target != null && this.target.hasPower("Sadness")) {
            addToTop((AbstractGameAction)new ApplyPowerAction(this.target, this.source, new SadnessPower(this.target, (this.target.getPower("Sadness")).amount),
                    (this.target.getPower("Sadness")).amount));
        }
        this.isDone = true;
    }
}