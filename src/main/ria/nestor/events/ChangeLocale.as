package nestor.events {
import flash.events.Event;

/**
 * This is not used.
 * You might want to dispatch a specific event when locale changes.
 * This allows you to create a shortcut method for :
 * resourceManager.getString('resourceFileName', 'key')
 *
 * You might want to consider performance impacts before doing so.
 */
public class ChangeLocale extends Event {
    public function ChangeLocale() {
        super(Event.CHANGE, true)
    }
}
}