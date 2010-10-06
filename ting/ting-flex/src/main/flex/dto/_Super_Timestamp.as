/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - Timestamp.as.
 */

package dto
{
import com.adobe.fiber.services.IFiberManagingService;
import com.adobe.fiber.valueobjects.IValueObject;
import flash.events.EventDispatcher;
import mx.events.PropertyChangeEvent;

import flash.net.registerClassAlias;
import flash.net.getClassByAlias;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;

use namespace model_internal;

[ExcludeClass]
public class _Super_Timestamp extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
        try
        {
            if (flash.net.getClassByAlias("java.sql.Timestamp") == null)
            {
                flash.net.registerClassAlias("java.sql.Timestamp", cz);
            }
        }
        catch (e:Error)
        {
            flash.net.registerClassAlias("java.sql.Timestamp", cz);
        }
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
    }

    model_internal var _dminternal_model : _TimestampEntityMetadata;

    /**
     * properties
     */
    private var _internal_nanos : int;
    private var _internal_time : Number;
    private var _internal_minutes : int;
    private var _internal_seconds : int;
    private var _internal_hours : int;
    private var _internal_month : int;
    private var _internal_timezoneOffset : int;
    private var _internal_year : int;
    private var _internal_day : int;
    private var _internal_date : int;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_Timestamp()
    {
        _model = new _TimestampEntityMetadata(this);

        // Bind to own data properties for cache invalidation triggering

    }

    /**
     * data property getters
     */

    [Bindable(event="propertyChange")]
    public function get nanos() : int
    {
        return _internal_nanos;
    }

    [Bindable(event="propertyChange")]
    public function get time() : Number
    {
        return _internal_time;
    }

    [Bindable(event="propertyChange")]
    public function get minutes() : int
    {
        return _internal_minutes;
    }

    [Bindable(event="propertyChange")]
    public function get seconds() : int
    {
        return _internal_seconds;
    }

    [Bindable(event="propertyChange")]
    public function get hours() : int
    {
        return _internal_hours;
    }

    [Bindable(event="propertyChange")]
    public function get month() : int
    {
        return _internal_month;
    }

    [Bindable(event="propertyChange")]
    public function get timezoneOffset() : int
    {
        return _internal_timezoneOffset;
    }

    [Bindable(event="propertyChange")]
    public function get year() : int
    {
        return _internal_year;
    }

    [Bindable(event="propertyChange")]
    public function get day() : int
    {
        return _internal_day;
    }

    [Bindable(event="propertyChange")]
    public function get date() : int
    {
        return _internal_date;
    }

    /**
     * data property setters
     */

    public function set nanos(value:int) : void
    {
        var oldValue:int = _internal_nanos;
        if (oldValue !== value)
        {
            _internal_nanos = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "nanos", oldValue, _internal_nanos));
        }
    }

    public function set time(value:Number) : void
    {
        var oldValue:Number = _internal_time;
        if (oldValue !== value)
        {
            _internal_time = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "time", oldValue, _internal_time));
        }
    }

    public function set minutes(value:int) : void
    {
        var oldValue:int = _internal_minutes;
        if (oldValue !== value)
        {
            _internal_minutes = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "minutes", oldValue, _internal_minutes));
        }
    }

    public function set seconds(value:int) : void
    {
        var oldValue:int = _internal_seconds;
        if (oldValue !== value)
        {
            _internal_seconds = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "seconds", oldValue, _internal_seconds));
        }
    }

    public function set hours(value:int) : void
    {
        var oldValue:int = _internal_hours;
        if (oldValue !== value)
        {
            _internal_hours = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "hours", oldValue, _internal_hours));
        }
    }

    public function set month(value:int) : void
    {
        var oldValue:int = _internal_month;
        if (oldValue !== value)
        {
            _internal_month = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "month", oldValue, _internal_month));
        }
    }

    public function set timezoneOffset(value:int) : void
    {
        var oldValue:int = _internal_timezoneOffset;
        if (oldValue !== value)
        {
            _internal_timezoneOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "timezoneOffset", oldValue, _internal_timezoneOffset));
        }
    }

    public function set year(value:int) : void
    {
        var oldValue:int = _internal_year;
        if (oldValue !== value)
        {
            _internal_year = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "year", oldValue, _internal_year));
        }
    }

    public function set day(value:int) : void
    {
        var oldValue:int = _internal_day;
        if (oldValue !== value)
        {
            _internal_day = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "day", oldValue, _internal_day));
        }
    }

    public function set date(value:int) : void
    {
        var oldValue:int = _internal_date;
        if (oldValue !== value)
        {
            _internal_date = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "date", oldValue, _internal_date));
        }
    }

    /**
     * Data property setter listeners
     *
     * Each data property whose value affects other properties or the validity of the entity
     * needs to invalidate all previously calculated artifacts. These include:
     *  - any derived properties or constraints that reference the given data property.
     *  - any availability guards (variant expressions) that reference the given data property.
     *  - any style validations, message tokens or guards that reference the given data property.
     *  - the validity of the property (and the containing entity) if the given data property has a length restriction.
     *  - the validity of the property (and the containing entity) if the given data property is required.
     */


    /**
     * valid related derived properties
     */
    model_internal var _isValid : Boolean;
    model_internal var _invalidConstraints:Array = new Array();
    model_internal var _validationFailureMessages:Array = new Array();

    /**
     * derived property calculators
     */

    /**
     * isValid calculator
     */
    model_internal function calculateIsValid():Boolean
    {
        var violatedConsts:Array = new Array();
        var validationFailureMessages:Array = new Array();

        var propertyValidity:Boolean = true;

        model_internal::_cacheInitialized_isValid = true;
        model_internal::invalidConstraints_der = violatedConsts;
        model_internal::validationFailureMessages_der = validationFailureMessages;
        return violatedConsts.length == 0 && propertyValidity;
    }

    /**
     * derived property setters
     */

    model_internal function set isValid_der(value:Boolean) : void
    {
        var oldValue:Boolean = model_internal::_isValid;
        if (oldValue !== value)
        {
            model_internal::_isValid = value;
            _model.model_internal::fireChangeEvent("isValid", oldValue, model_internal::_isValid);
        }
    }

    /**
     * derived property getters
     */

    [Transient]
    [Bindable(event="propertyChange")]
    public function get _model() : _TimestampEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _TimestampEntityMetadata) : void
    {
        var oldValue : _TimestampEntityMetadata = model_internal::_dminternal_model;
        if (oldValue !== value)
        {
            model_internal::_dminternal_model = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "_model", oldValue, model_internal::_dminternal_model));
        }
    }

    /**
     * methods
     */


    /**
     *  services
     */
    private var _managingService:com.adobe.fiber.services.IFiberManagingService;

    public function set managingService(managingService:com.adobe.fiber.services.IFiberManagingService):void
    {
        _managingService = managingService;
    }

    model_internal function set invalidConstraints_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_invalidConstraints;
        // avoid firing the event when old and new value are different empty arrays
        if (oldValue !== value && (oldValue.length > 0 || value.length > 0))
        {
            model_internal::_invalidConstraints = value;
            _model.model_internal::fireChangeEvent("invalidConstraints", oldValue, model_internal::_invalidConstraints);
        }
    }

    model_internal function set validationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_validationFailureMessages;
        // avoid firing the event when old and new value are different empty arrays
        if (oldValue !== value && (oldValue.length > 0 || value.length > 0))
        {
            model_internal::_validationFailureMessages = value;
            _model.model_internal::fireChangeEvent("validationFailureMessages", oldValue, model_internal::_validationFailureMessages);
        }
    }


}

}
