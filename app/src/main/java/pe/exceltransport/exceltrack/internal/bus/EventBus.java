package pe.exceltransport.exceltrack.internal.bus;

import pe.exceltransport.domain.Tracking;

public class EventBus {

    private EventBus() {

    }

    public static class UpdateTrackingEvent {

        private Tracking tracking;

        public UpdateTrackingEvent(Tracking tracking) {
            this.tracking = tracking;
        }

        public Tracking getTracking() {
            return tracking;
        }
    }

}
