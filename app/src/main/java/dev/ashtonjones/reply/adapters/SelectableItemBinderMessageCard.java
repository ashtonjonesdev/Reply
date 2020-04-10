package dev.ashtonjones.reply.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.android.material.card.MaterialCardView;

import dev.ashtonjones.reply.R;
import dev.ashtonjones.reply.datamodels.MessageCard;

import mva2.adapter.ItemBinder;
import mva2.adapter.ItemViewHolder;

public class SelectableItemBinderMessageCard extends ItemBinder<MessageCard, SelectableItemBinderMessageCard.ViewHolder> {

    // LOG TAG
    private static final String LOG_TAG = SelectableItemBinderMessageCard.class.getSimpleName();

    public static int itemAdapterPosition = -1;

    @Override
    public SelectableItemBinderMessageCard.ViewHolder createViewHolder(ViewGroup parent) {

        return new SelectableItemBinderMessageCard.ViewHolder(inflate(parent, R.layout.messagecard_item));

    }

    @Override
    public void bindViewHolder(SelectableItemBinderMessageCard.ViewHolder holder, MessageCard item) {

        // If the card is selected, change the color and increase the card elevation

        int bgColor = ContextCompat.getColor(holder.materialCardView.getContext(),
                holder.isItemSelected() ? R.color.colorSelectedCardBackground : R.color.colorUnselectedCardBackground);

        holder.materialCardView.setCardBackgroundColor(bgColor);

        holder.materialCardView.setCardElevation(holder.isItemSelected() ? 12 : 4);

        // Set the card title
        holder.titleTextView.setText(item.getTitle());

        // Set the card message
        holder.cardMessage = item.getMessage();


    }

    @Override
    public boolean canBindData(Object item) {

        return item instanceof MessageCard;

    }

    // TODO: Issue with this class not being static?
    public class ViewHolder extends ItemViewHolder<MessageCard> {

        // Reference variables for the ViewHolder that represent the Views in the cards and the String for the message

        private TextView titleTextView;

        private String cardMessage;

        private MaterialCardView materialCardView;

//        /**
//         *
//         * Enable drag to reorder cards
//         *
//         * Does not persist
//         *
//         * @return
//         */
//        @Override public int getDragDirections() {
//            return ItemTouchHelper.LEFT
//                    | ItemTouchHelper.UP
//                    | ItemTouchHelper.RIGHT
//                    | ItemTouchHelper.DOWN;
//        }

        public ViewHolder(View itemView) {

            super(itemView);

            titleTextView = itemView.findViewById(R.id.message_fragment_card_view_title_text_view);

            materialCardView = itemView.findViewById(R.id.message_fragment_card_view);

            // Select/Deselect the card on click
            itemView.setOnClickListener(view -> {

                itemAdapterPosition = getAdapterPosition();

                toggleItemSelection();

            });



//
//            // Enable drag and drop on long press
//            itemView.setOnLongClickListener(v -> {
//                startDrag();
//                return true;
//            });

        }


    }


}


