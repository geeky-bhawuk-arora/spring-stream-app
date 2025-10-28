import { AlertCircle, CheckCircle } from 'lucide-react';

export default function Message({ message }) {
  if (!message) return null;

  return (
    <div
      className={`p-3 rounded-md flex items-start gap-2 text-sm ${
        message.type === 'error'
          ? 'bg-red-50 text-red-800'
          : 'bg-green-50 text-green-800'
      }`}
    >
      {message.type === 'error' ? (
        <AlertCircle className="w-4 h-4 mt-0.5 flex-shrink-0" />
      ) : (
        <CheckCircle className="w-4 h-4 mt-0.5 flex-shrink-0" />
      )}
      <span>{message.text}</span>
    </div>
  );
}